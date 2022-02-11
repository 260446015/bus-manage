package com.example.busmanage.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class QueryDto {
    private int pn = 0;
    private int ps = 10;
    private String search;
    private String name;
    private String username;
    private String mobile;
    private Boolean online;
    private String busNum;
    private Map<String,Object> param;

}
