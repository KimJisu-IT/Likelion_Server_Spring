package com.sparta.week04.repository;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class ItemDto {
    private final String title;
    private final String link;
    private final String image;
    private final int lprice;

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }

}