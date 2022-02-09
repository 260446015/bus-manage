package com.example.busmanage.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryDto {
    private int pn = 0;
    private int ps = 10;
    private String search;
    private String name;
    private String username;
    private String mobile;

}
