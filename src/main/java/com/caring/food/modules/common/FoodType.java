package com.caring.food.modules.common;

public enum FoodType {
    KOREAN("한식"), CHINESE("중식"), WESTERN("양식"), JAPANESE("일식"), SOMETHING("기타"), DELIVERY("배달");

    final private String name;

    public String getName() {
        return name;
    }
    FoodType(String name){
        this.name = name;
    }
}
