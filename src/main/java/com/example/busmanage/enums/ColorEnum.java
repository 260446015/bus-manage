package com.example.busmanage.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ColorEnum {
    YELLOW("黄色"),GREEN("绿色"),RED("红色"),WHITE("白色"),BLACK("黑色");

    private String color;
    ColorEnum(String color) {
        this.color = color;
    }

    @JsonValue
    public String getColor() {
        return color;
    }
}
