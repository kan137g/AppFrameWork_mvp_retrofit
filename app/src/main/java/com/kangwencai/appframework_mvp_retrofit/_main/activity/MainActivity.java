package com.kangwencai.appframework_mvp_retrofit._main.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.kangwencai.appframework_mvp_retrofit.R;
import com.kangwencai.business.activity.ZhihuStoryListActivity;
import com.kangwencai.common.base.BaseActivity;
import com.kangwencai.common.utils.LogUtils;

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
        LogUtils.init();

        //包名
    }

    @Override
    public void initListener() {
        super.initListener();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
//                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_dashboard:
//                        mTextMessage.setText(R.string.title_dashboard);
                        return true;
                    case R.id.navigation_notifications:
//                        mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            }
        };
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
