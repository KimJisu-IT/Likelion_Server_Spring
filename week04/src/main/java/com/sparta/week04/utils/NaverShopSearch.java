package com.sparta.week04.utils;

import com.sparta.week04.repository.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class NaverShopSearch {
    public String search(String 아이맥) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "jySUWjvzAV8jTozJs89j");
        headers.add("X-Naver-Client-Secret", "wbVVwWGu5E");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=adidas", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {

        JSONObject rjson=new JSONObject(result);
        JSONArray items=rjson.getJSONArray("items");
        List<ItemDto> ret=new ArrayList<>();

        for(int i=0;i<items.length();i++){
            JSONObject itemJson=(JSONObject) items.get(i);
            //System.out.println(itemJson);
            ItemDto itemDto=new ItemDto(itemJson);
            ret.add(itemDto);
        }

        return ret;

    }
}