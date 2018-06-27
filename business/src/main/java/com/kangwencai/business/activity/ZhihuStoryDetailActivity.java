package com.kangwencai.business.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kangwencai.business.R;
import com.kangwencai.business.activity.IView.IHttpsActivity;
import com.kangwencai.business.activity.IView.IZhihuStoryDetailActivity;
import com.kangwencai.business.model.bean.zhihu.ZhihuStoryContent;
import com.kangwencai.business.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.business.presenter.ZhihuStoryDetailPresenter;
import com.kangwencai.common.IConstantRouterPages;
import com.kangwencai.common.base.BaseActivity;
import com.kangwencai.common.utils.StatusBarCompat;
import com.kangwencai.common.utils.WebUtils;
import com.kangwencai.common.utils.img_utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ZhihuStoryDetailActivity extends BaseActivity implements IZhihuStoryDetailActivity {


    private android.widget.ImageView storyimg;
    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.CollapsingToolbarLayout toolbarlayout;
    private android.support.design.widget.AppBarLayout appbar;
    private android.support.design.widget.FloatingActionButton fab;

    private  WebView mZhihudailyWebview;

    private ZhihuStoryDetailPresenter mPresenter = new ZhihuStoryDetailPresenter(this);
    private String story_id;

    @Override
    public void fillView() {
        setContent(R.layout.activity_zhihu_story_detail);
        this.fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        this.appbar = (android.support.design.widget.AppBarLayout) findViewById(R.id.app_bar);
        this.toolbarlayout = (android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.storyimg = (ImageView) findViewById(R.id.story_img);
        mZhihudailyWebview = findViewById(R.id.zhihudaily_webview);


        StatusBarCompat.compat(this);
//        findViewById(R.id.)


    }

    @Override
    public void initViewFromXML() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(IConstantRouterPages.ForRouterTestActivity).navigation();

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhihuStoryDetailActivity.this.finish();
            }
        });
    }

    @Override
    public void initData() {
        story_id = getIntent().getIntExtra("id",0) + "";
        mPresenter.loadStory(story_id);
    }


    @Override
    public void loadZhihuStory() {

    }

    @Override
    public void loadFail(String errmsg) {

    }

    @Override
    public void loadSuccess(ZhihuStoryContent zhihuStory) {

        GlideUtils.showImageView(mContext,zhihuStory.getImage(),storyimg);
        String url = zhihuStory.getShare_url();
        boolean isEmpty = TextUtils.isEmpty(zhihuStory.getBody());
        String mBody = zhihuStory.getBody();
        String[] scc = zhihuStory.getCss();
        //如果返回的html body为空则直接 load url
        if (isEmpty) {
            mZhihudailyWebview.loadUrl(url);
        } else {
            String data = WebUtils.buildHtmlWithCss(mBody, scc, false);
            mZhihudailyWebview.loadDataWithBaseURL(WebUtils.BASE_URL, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, WebUtils.FAIL_URL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.dispose();
    }
}
