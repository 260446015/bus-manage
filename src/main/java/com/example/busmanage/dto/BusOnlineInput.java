package com.example.busmanage.dto;

import com.example.busmanage.enums.BusType;
import lombok.Data;

import javax.persistence.Column;

@Data
public class BusOnlineInput {

    private String id;
    private String name;
    private String busName;
    private String busNum;
    private BusType busType;
    private Boolean outerOrInner;
    private String busId;
    private String driverId;
}
