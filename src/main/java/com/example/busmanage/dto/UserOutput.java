package com.example.busmanage.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author dawei.yin
 * @version 1.0
 * @date 2022/2/17 18:51
 */
@Data
public class UserOutput {
    private String username;
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
