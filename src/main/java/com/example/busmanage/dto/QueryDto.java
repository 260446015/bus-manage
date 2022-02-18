package com.example.busmanage.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.busmanage.util.JsonUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;

@Data
@Accessors(chain = true)
public class QueryDto<T> {
    private int pn = 0;
    private int limit = 10;
    private String search;
    private String type;
    private String name;
    private String username;
    private Boolean online;
    private String busNum;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JSONObject convertToJson() {
        JSONObject data = JSONObject.parseObject(JsonUtils.toUnderlineJSONString(this));
        data.remove("pn");
        data.remove("limit");
        data.remove("type");
        data.remove("search");
        return data;
    }

    public  QueryWrapper<T> buildQuery() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        JSONObject data = convertToJson();
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            String value = data.get(key).toString();
            if (Objects.nonNull(data.get(key)) && !StringUtils.isEmpty(value)) {
                queryWrapper.like(key, value);
            }
        }
        return queryWrapper;
    }
}
