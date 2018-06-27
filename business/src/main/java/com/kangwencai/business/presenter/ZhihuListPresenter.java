package com.kangwencai.business.presenter;

import android.content.Context;

import com.kangwencai.business.activity.IView.IHttpsActivity;
import com.kangwencai.business.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.business.model.biz.ZhihuDailyBiz;
import com.kangwencai.common.base.BasePresenter;
import com.kangwencai.common.model.listener.INetCallBack;

import java.util.ArrayList;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public class ZhihuListPresenter extends BasePresenter {

    private IHttpsActivity mActivity;
    private ZhihuDailyBiz mZhihuDailyBiz;

    public ZhihuListPresenter(IHttpsActivity mainActivity) {
        this.mActivity = mainActivity;
        mZhihuDailyBiz = new ZhihuDailyBiz();
    }

    public void getStoryDataByRetrofit(Context context) {
        mZhihuDailyBiz.getStoryDataByRetrofit(new INetCallBack<ArrayList<ZhihuTopStory>>() {
            @Override
            public void onSuccess(ArrayList<ZhihuTopStory> response) {
                mActivity.showResult(response);
            }

            @Override
            public void onFail(String errMsg) {

            }

            @Override
            public void onNetError(String errMsg) {

            }
        });



    }

}
