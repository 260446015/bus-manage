package com.example.busmanage.entity;

import com.example.busmanage.dto.ColorEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    private String id;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "车牌号不能为空")
    private String busNum;
    @Column(columnDefinition = "varchar(10) not null")
    private BusType busType;
    @Column(columnDefinition = "varchar(10)")
    private ColorEnum colorEnum;
    @Column(columnDefinition = "varchar(10) not null")
    private BusStatus busStatus;
    private Boolean online;

    enum BusType{
        TRUCK,CAR,BUS
    }

    enum BusStatus {
        OK,ERR
    }
}
