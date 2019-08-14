package com.vanke.commonlib.net.service;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by kangwencai on 2016/11/15.
 */

public class MyRequestBodyCreator {
    public static RequestBody create(String gson) {

        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), gson);

    }
}
