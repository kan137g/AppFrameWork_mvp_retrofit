package com.kangwencai.business.net;


import com.kangwencai.common.IConstants;
import com.kangwencai.common.net_retrofit.RetrofitRest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kangwencai on 2016/11/9.
 */
public class RetrofitRestBusiness extends RetrofitRest {

    public static BusinessNetService businessNetService;

    public static BusinessNetService getInstance() {

        if (businessNetService == null) {
            businessNetService = RetrofitRest.getRetrofit().create(BusinessNetService.class);
        }


        return businessNetService;
    }


}
