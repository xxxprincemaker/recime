package com.example.recime.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {
    private String id;
    private String name;
    private String description;
    private String imageUrl = "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg";
    private String recipe;
    private Integer position;
}
