package com.kangwencai.appframework_mvp_retrofit._main.activity;

import android.app.ActivityManager;
import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kangwencai.appframework_mvp_retrofit.R;
import com.kangwencai.common.IConstantRouterPages;
import com.kangwencai.common.base.BaseActivity;
import com.kangwencai.common.utils.LogUtils;

import java.util.List;

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
        getRunningActivityName();

    }

    private void getRunningActivityName() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskInfoList= manager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo info =taskInfoList.get(0);

        String shortClassName = info.topActivity.getShortClassName();    //类名
        String className = info.topActivity.getClassName();              //完整类名
        String packageName = info.topActivity.getPackageName();
        LogUtils.e(className);
    }
}
