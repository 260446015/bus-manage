package com.example.busmanage.entity;

import com.example.busmanage.enums.BusType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bus_online")
public class BusOnline {
    @Id
    private String id;
    private String driverId;
    private String busId;
    private Boolean outerOrInner;
    private Date createTime;
    private Date innerTime;
    private Date outerTime;
}
