package com.example.busmanage.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BusStatus {
    OK("正常"), ERR("维修中");

    private String status;

    BusStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
