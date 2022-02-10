package com.example.busmanage.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    private String id;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    private Character sex;
    private Integer age;
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "手机号格式不正确")
    private String mobile;
    private String address;
    private Boolean online;
}
