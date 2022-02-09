package com.example.busmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    private String id;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "idkey不能为空")
    private String idKey;
}
