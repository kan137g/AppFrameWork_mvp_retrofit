package com.kangwencai.appframework_mvp_retrofit._main.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kangwencai.appframework_mvp_retrofit.R;
import com.kangwencai.common.IConstantRouterPages;
import com.kangwencai.common.base.BaseActivity;
import com.kangwencai.common.utils.LogUtils;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

@Route(path = IConstantRouterPages.ForRouterTestActivity)
public class ForRouterTestActivity extends BaseActivity {


//    private MainActivityPresenter mPresenter = new MainActivityPresenter(this);


    @Override
    public void fillView() {
        setContent(R.layout.activity_for_router_test);
        ButterKnife.bind(this);

        Logger.e("============");
        LogUtils.e(getRunningActivityName());
    }

    @Override
    public void initViewFromXML() {

    }

    @Override
    public void initData() {

    }

    private String getRunningActivityName() {
        String contextString = mContext.toString();
        return contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
    }
}
