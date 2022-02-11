package com.example.busmanage.dto;

import com.example.busmanage.entity.Bus;
import com.example.busmanage.entity.Driver;
import com.example.busmanage.enums.BusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BusOnlineDTO {

    private String id;
    private String name;
    private String busName;
    private String busNum;
    private BusType busType;
    private Boolean outerOrInner;
    private String busId;
    private String driverId;
    private Bus bus;
    private Driver driver;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date innerTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date outerTime;
}
