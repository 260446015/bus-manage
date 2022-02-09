package com.example.busmanage.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BusType {
    TRUCK("货车"),CAR("小汽车"),BUS("公交车");

    private String busType;

    BusType(String busType) {
        this.busType = busType;
    }

    @JsonValue
    public String getBusType() {
        return busType;
    }
}
