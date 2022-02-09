package com.example.busmanage.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    private String id;
    private String name;
    private int age;
    private String mobile;
    private String address;
    private Boolean outerOrInner;
}
