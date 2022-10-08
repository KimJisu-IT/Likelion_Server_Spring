package com.sparta.week04.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import org.springframework.http.*;

public class NaverShopSearch {
    public String search(String 아이맥){
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
    public static void main(String[] args){
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        naverShopSearch.search("아이맥");
    }
}