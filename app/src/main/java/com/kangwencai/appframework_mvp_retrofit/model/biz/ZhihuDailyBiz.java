package com.kangwencai.appframework_mvp_retrofit.model.biz;

import android.os.Handler;

import com.google.gson.Gson;
import com.kangwencai.appframework_mvp_retrofit.model.bean.zhihu.ZhiHuDaily;
import com.kangwencai.appframework_mvp_retrofit.model.bean.zhihu.ZhihuStory;
import com.kangwencai.appframework_mvp_retrofit.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.appframework_mvp_retrofit.net.RetrofitRestApp;
import com.kangwencai.common.model.listener.INetCallBack;
import com.kangwencai.common.net_ok_http.HttpServiceManager;
import com.kangwencai.common.net_retrofit.RetrofitRest;
import com.kangwencai.common.net_retrofit.rest.RestCallBack;
import com.kangwencai.common.net_retrofit.rest.RestCallBackNew;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: 单纯用MVP用这种方式就差不多了
 * <p>
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */

public class ZhihuDailyBiz {
    /**
     * 用OKHttp获取数据的实例
     * @param url
     * @param eventLister
     */
    public void getStoryData(final String url, final INetCallBack<ArrayList<ZhihuStory>> eventLister) {
        final Handler handler = new Handler();
        new Thread() {
            public void run() {
                try {
                    String result = HttpServiceManager.httpGet(url);
                    Gson gson = new Gson();
                    ZhiHuDaily daily = gson.fromJson(result, ZhiHuDaily.class);
                    final ArrayList<ZhihuStory> stories = daily.getStories();
                    if (stories != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                eventLister.onSuccess(stories);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                eventLister.onFail("获取日报失败！");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            eventLister.onFail("获取日报失败！");
                        }
                    });
                }
            }
        }.start();
    }

    /**
     * 获取知乎的文章列表，封装返回数据，retrofit框架解析一次，到了具体事项再解析一次，数据返回时候开销增大，但是数据
     *
     * @param eventLister
     */
//    public void getStoryDataByRetrofit(final INetCallBack<ArrayList<ZhihuStory>> eventLister) {
//
//    }

    /**
     * 获取知乎的文章列表，不封装返回数据，retrofit框架直接解析，浪费一些内存(储存了返回数据的全部)，可能会让数据结构特别复杂
     *
     * @param eventLister
     */
    public void getStoryDataByRetrofit(final INetCallBack<ArrayList<ZhihuTopStory>> eventLister) {
        RetrofitRestApp.appNetService.getZhihuDailyRetrofitOnly2().enqueue(new Callback<ZhiHuDaily>() {
            @Override
            public void onResponse(Call<ZhiHuDaily> call, Response<ZhiHuDaily> response) {
                ZhiHuDaily daily = response.body();
                eventLister.onSuccess(daily.getTop_stories());
            }

            @Override
            public void onFailure(Call<ZhiHuDaily> call, Throwable t) {
                eventLister.onFail(t.getMessage());
            }
        });

    }
}
