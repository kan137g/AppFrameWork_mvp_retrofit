package com.kangwencai.appframework_mvp_retrofit.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //打印请求链接
        String TAG_REQUEST = "request";
        Log.e(TAG_REQUEST, request.url().toString());
        Response response = chain.proceed(request);
        //打印返回的message
        return response;
    }
}
