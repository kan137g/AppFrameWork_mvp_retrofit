package com.kangwencai.business.presenter;


import com.kangwencai.business.activity.IView.IZhihuStoryDetailActivity;
import com.kangwencai.business.model.bean.zhihu.ZhihuStoryContent;
import com.kangwencai.business.net.RetrofitRestBusiness;
import com.kangwencai.common.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
//public class ZhihuStoryDetailPresenter extends BasePresenter {
public class ZhihuStoryDetailPresenter extends BasePresenter {
    IZhihuStoryDetailActivity mActivity;

    public ZhihuStoryDetailPresenter(IZhihuStoryDetailActivity zhihuStoryDetailActivity) {
        this.mActivity = zhihuStoryDetailActivity;
    }

    public void loadStory(String id) {

        RetrofitRestBusiness.businessNetService
                .getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStoryContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ZhihuStoryContent zhihuStoryContent) {
                        mActivity.loadSuccess(zhihuStoryContent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.loadFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
