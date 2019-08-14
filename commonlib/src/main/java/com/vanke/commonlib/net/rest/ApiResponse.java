package com.vanke.commonlib.net.rest;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by kangwencai on 2016/11/15.
 * 对网络返回结果的封装
 */
public class ApiResponse {
    /*返回码，根据返回码可以确认返回的状态*/
    private String code;
    /*返回的有效数据*/
    private JSONObject data;

    /*返回的提示性信息*/
    private String message;
    private String sign;

    public ApiResponse( ) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
