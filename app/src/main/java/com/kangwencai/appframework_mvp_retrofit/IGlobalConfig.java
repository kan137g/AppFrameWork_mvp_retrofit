package com.kangwencai.appframework_mvp_retrofit;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public interface IGlobalConfig {
    /**
     * 标示是否是 debug 版本
     */
    boolean isDebug = true;
    String baseUrl = "http://news-at.zhihu.com/api/4/";


    String netErrorTips = "网络请求异常";
}
