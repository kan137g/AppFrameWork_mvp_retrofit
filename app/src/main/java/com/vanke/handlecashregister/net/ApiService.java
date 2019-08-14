package com.vanke.handlecashregister.net;

import com.vanke.commonlib.net.rest.ApiResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2019-08-14.
 * PS: Not easy to write code, please indicate.
 */
public interface ApiService {
    @POST("hiamw/api/sale/begin")
    Call<ApiResponse> begin(@Body RequestBody body);

 }
