package com.kangwencai.appframework_mvp_retrofit.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kangwencai.appframework_mvp_retrofit.R;
import com.kangwencai.common.IConstantRouterPages;
import com.kangwencai.common.base.BaseActivity;

import butterknife.ButterKnife;

@Route(path = IConstantRouterPages.ForRouterTestActivity)
public class ForRouterTestActivity extends BaseActivity {


//    private MainActivityPresenter mPresenter = new MainActivityPresenter(this);


    @Override
    public void fillView() {
        setContent(R.layout.activity_for_router_test);
        ButterKnife.bind(this);
    }

    @Override
    public void initViewFromXML() {

    }

    @Override
    public void initData() {

    }


}
