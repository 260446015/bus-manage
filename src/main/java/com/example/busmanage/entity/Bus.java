package com.example.busmanage.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.busmanage.enums.ColorEnum;
import com.example.busmanage.enums.BusStatus;
import com.example.busmanage.enums.BusType;
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
    @NotEmpty(message = "车辆名称不能为空")
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
}
