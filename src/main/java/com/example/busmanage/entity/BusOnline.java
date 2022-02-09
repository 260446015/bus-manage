package com.example.busmanage.entity;

import lombok.Data;
import lombok.experimental.Accessors;

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
    private String name;
    private Date beginDate;
    private Date endDate;
}
