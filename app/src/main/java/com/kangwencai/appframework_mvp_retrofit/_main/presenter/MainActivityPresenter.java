package com.kangwencai.appframework_mvp_retrofit._main.presenter;

import android.content.Context;

import com.kangwencai.appframework_mvp_retrofit._main.activity.IView.IHttpsActivity;

import com.kangwencai.appframework_mvp_retrofit._main.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.appframework_mvp_retrofit._main.model.biz.ZhihuDailyBiz;

import com.kangwencai.common.base.BasePresenter;
import com.kangwencai.common.model.listener.INetCallBack;

import java.util.ArrayList;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */

public class MainActivityPresenter extends BasePresenter {

    private IHttpsActivity mActivity;
    private ZhihuDailyBiz mZhihuDailyBiz;

    public MainActivityPresenter(IHttpsActivity mainActivity) {
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


//            RetrofitRestBusiness.businessNetService
//                    .get12306Test()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<ResponseBody>() {
//                        @Override
//                        public void onSubscribe(@NonNull Disposable d) {
//                            addDisposable(d);
//                        }
//
//                        @Override
//                        public void onNext(@NonNull ResponseBody responseBody) {
//                            try {
//                                mActivity.showResult(responseBody.string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            mActivity.showResult(e.getMessage());
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//
    }

}
