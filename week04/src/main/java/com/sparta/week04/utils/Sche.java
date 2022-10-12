package com.sparta.week04.utils;

import com.sparta.week04.repository.ItemDto;
import com.sparta.week04.repository.Product;
import com.sparta.week04.repository.ProductRepository;
import com.sparta.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Sche {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    @Scheduled(cron="0 0 1 * * *") //초, 분, 시, 일 ,월 , 주 순서
    public void updatePrice() throws InterruptedException{
        System.out.println("가격 업데이트 실행");

        List<Product> productList=productRepository.findAll();
        for(int i=0;i<productList.size();i++){ //전체 관심 상품 조회
            TimeUnit.SECONDS.sleep(1); //1초에 한 상품씩 조회(네이버 제한)
            Product p=productList.get(i); //관심상품 꺼내기
            String title=p.getTitle();
            String resultString=naverShopSearch.search(title); //관심상품 제목으로 검색

            List<ItemDto> itemDtoList=naverShopSearch.fromJSONtoItems(resultString);
            ItemDto itemDto=itemDtoList.get(0); //관심상품 검색 목록에서 첫번째 결과를 꺼내기
            Long id=p.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}
