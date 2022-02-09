package com.example.busmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BusDto {
    @NotEmpty(message = "xxxxx")
    private String name;
}
