package com.kangwencai.common.net_retrofit.rest;

import com.google.gson.JsonObject;

/**
 * Created by kangwencai on 2016/11/15.
 * 对网络返回结果的封装
 */
public class ApiResponse {
    /*返回码，根据返回码可以确认返回的状态*/
    private String code;
    /*返回的有效数据*/
    private JsonObject result;
    /*返回的提示性信息*/
    private String summary;

    public ApiResponse( ) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
