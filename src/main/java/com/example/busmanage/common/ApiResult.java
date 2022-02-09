package com.example.busmanage.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

public class ApiResult implements Serializable {
    private static final long serialVersionUID = 8052566108151898248L;
    public static final String SUCCESS_MESSAGE_CODE = "操作成功";
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_ERROR = -3;
    public static final int SYSTEM_ERROR = -4;
    private int code = 0;
    private String message = "";
    private Object data;

    public int getCode() {
        return this.code;
    }

    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public ApiResult setData(Object data) {
        this.data = data;
        return this;
    }

    public static ApiResult ok(Object t){
        ApiResult apiResult = new ApiResult();
        apiResult.setData(t).setCode(0);
        return apiResult;
    }

    public static ApiResult ok(){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(0).setMessage(SUCCESS_MESSAGE_CODE);
        return apiResult;
    }

    public static ApiResult error(String object){
        ApiResult apiResult = new ApiResult();
        apiResult.setMessage(object).setCode(-1);
        return apiResult;
    }

    public static ApiResult successPages(IPage data) {
        JSONObject result = new JSONObject();
        result.put("dataList", data.getRecords());
        result.put("totalNumber", data.getTotal());
        result.put("totalPage", data.getPages());
        result.put("pageNumber", data.getCurrent());
        return new ApiResult().setData(result).setCode(0).setMessage("消息返回成功");
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}


