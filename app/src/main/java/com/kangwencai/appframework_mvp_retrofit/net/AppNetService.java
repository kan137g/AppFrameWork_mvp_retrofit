package com.kangwencai.appframework_mvp_retrofit.net;

import com.kangwencai.appframework_mvp_retrofit._main.model.bean.zhihu.ZhiHuDaily;
import com.kangwencai.appframework_mvp_retrofit._main.model.bean.zhihu.ZhihuStoryContent;
import com.kangwencai.common.net_retrofit.rest.ApiResponse;



import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/21.
 * PS: Not easy to write code, please indicate.
 */
public interface AppNetService {
    //单纯使用retrofit接口定义
    @GET("news/latest")
    Call<ZhiHuDaily> getZhihuDailyRetrofitOnly();

    //单纯使用retrofit接口定义
    @GET("news/latest")
    Call<ZhiHuDaily> getZhihuDailyRetrofitOnly2();

    //使用retrofit+RxAndroid的接口定义
    @GET("news/latest")
    Observable<ZhiHuDaily> getZhihuDaily();

    @GET("/")
    Observable<ResponseBody> get12306Test();

    @GET("news/{id}")
    Observable<ZhihuStoryContent> getStoryContent(@Path("id") String id);

    @POST("test/")
    Call<ApiResponse> getWeather(@Query("q") String strCity);

    @POST("test/")
    Call<ApiResponse> getWeather(@Body RequestBody body);


}
