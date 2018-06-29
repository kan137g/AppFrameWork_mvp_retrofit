package com.kangwencai.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kangwencai.business.R;
import com.kangwencai.business.activity.IView.IHttpsActivity;
import com.kangwencai.business.adapter.ZhihuListsAdapter;
import com.kangwencai.business.model.bean.zhihu.ZhihuTopStory;
import com.kangwencai.business.presenter.ZhihuListPresenter;
import com.kangwencai.common.base.BaseActivity;
import com.kangwencai.common.utils.LogUtils;
import com.kangwencai.common.utils.StatusBarCompat;


import java.util.ArrayList;

import butterknife.ButterKnife;


public class ZhihuStoryListActivity extends BaseActivity implements IHttpsActivity {


//    @BindView(R.id.operators_recycler)
//    RecyclerView operatorsRecycler;
//    @BindView(R.id.operators_refresh)
//    SwipeRefreshLayout operatorsRefresh;
//

    private ZhihuListsAdapter mZhihuListsAdapter;
    private ArrayList<ZhihuTopStory> storyArrayList = new ArrayList<>();
    private ZhihuListPresenter mPresenter = new ZhihuListPresenter(this);


    private RecyclerView operatorsrecycler;
    private SwipeRefreshLayout operatorsrefresh;


    @Override
    public void fillView() {
        setContent(R.layout.activity_zhihulist);
        this.operatorsrefresh = (SwipeRefreshLayout) findViewById(R.id.operators_refresh);
        this.operatorsrecycler = (RecyclerView) findViewById(R.id.operators_recycler);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this);
//        findViewById(R.id.)


    }

    @Override
    public void initViewFromXML() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        operatorsrecycler.setLayoutManager(layoutManager);
        operatorsrecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        operatorsrecycler.setPullRefreshEnabled(false);
//        operatorsrecycler.setLoadingMoreEnabled(false);
//        operatorsrefresh.setOnRefreshListener();
        operatorsrefresh.setEnabled(false);
        mZhihuListsAdapter = new ZhihuListsAdapter(mContext, storyArrayList);
        operatorsrecycler.setAdapter(mZhihuListsAdapter);

        mZhihuListsAdapter.setOnItemClickListener(new ZhihuListsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ZhihuStoryDetailActivity.class);

                intent.putExtra("title", storyArrayList.get(position).getTitle());
                intent.putExtra("id", storyArrayList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getStoryDataByRetrofit(this);
    }


    @Override
    public void showResult(ArrayList<ZhihuTopStory> response) {
        storyArrayList.addAll(response);
        mZhihuListsAdapter.notifyDataSetChanged();
    }


//    @OnClick(R.id.tonews)
//    public void onViewClicked() {
//        mPresenter.getStoryDataByRetrofit(this);
//    }
}
