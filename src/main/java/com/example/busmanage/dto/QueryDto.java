package com.example.busmanage.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class QueryDto {
    private int pn = 0;
    private int limit = 10;
    private String search;
    private String type;
    private String name;
    private String username;

}
