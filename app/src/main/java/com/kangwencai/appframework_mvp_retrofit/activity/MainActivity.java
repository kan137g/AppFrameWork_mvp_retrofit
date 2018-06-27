package com.kangwencai.appframework_mvp_retrofit.activity;

import android.content.Intent;
import android.view.View;

import com.kangwencai.appframework_mvp_retrofit.R;
import com.kangwencai.business.activity.ZhihuStoryListActivity;
import com.kangwencai.common.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


//    private MainActivityPresenter mPresenter = new MainActivityPresenter(this);


    @Override
    public void fillView() {
        setContent(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initViewFromXML() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_jump_to_zhihu_list, R.id.tonews})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_jump_to_zhihu_list:
                startActivity(new Intent(this, ZhihuStoryListActivity.class));
                break;
            case R.id.tonews:
                break;
        }
    }
}
