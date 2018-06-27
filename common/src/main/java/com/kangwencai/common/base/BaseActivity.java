package com.kangwencai.common.base;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.kangwencai.common.R;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    protected String TAG = getClass().getSimpleName();
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseActivity.this.handleMessage(msg);
        }
    };
    private FrameLayout titleBar;
    private FrameLayout content;

    protected RelativeLayout titlebar_rl;
    protected ImageView titlebar_back_view;
    protected TextView titlebar_title_tv;
    protected RelativeLayout titlebar_right_rl;
    protected ImageView titlebar_right_view;
    protected TextView titlebar_right_tv;
    protected Context mContext;
    protected BaseActivity mAct;
    protected Application application;

    //    protected CommitDataAnim mProgressDialog;
    private boolean mIsProgressDialogShow = false;

    protected void handleMessage(Message msg) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = getApplicationContext();
        application= (Application) getApplicationContext();
        mAct = this;
        titleBar = (FrameLayout) findViewById(R.id.activity_base_titlebar);
        content = (FrameLayout) findViewById(R.id.activity_base_content);
        deviceWidth = getWindowManager().getDefaultDisplay().getWidth();
        deviceHeight = getWindowManager().getDefaultDisplay().getHeight();

        setTitleBar(R.layout.activity_base_titlebar);
        showTitleBar();
        initTiltleBar();

        fillView();
        initViewFromXML();
        initData();
        initListener();
    }



    /**
     * 初始化监听,使用了butterKnife以后用到这里的位置比较少
     */
    public void initListener() {
    }


    protected int deviceWidth;
    protected int deviceHeight;

    /**
     * 设置内容布局,
     *
     * @param layoutResID
     */
    protected void setContent(int layoutResID) {
        content.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, content);
    }

    /**
     * 设置标题栏布局
     *
     * @param layoutResID
     */
    public void setTitleBar(int layoutResID) {
        titleBar.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, titleBar);
    }

    /**
     * 设置标题栏
     *
     * @param child
     */
    public void setTitleBar(View child) {
        titleBar.removeAllViews();
        titleBar.addView(child);
    }

    /**
     * 显示标题栏
     */
    protected void showTitleBar() {
        titleBar.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化标题栏
     */
    private void initTiltleBar() {
        titlebar_rl = (RelativeLayout) findViewById(R.id.titlebar_rl);
        titlebar_back_view = (ImageView) findViewById(R.id.titlebar_back_view);
        titlebar_title_tv = (TextView) findViewById(R.id.titlebar_title_tv);
        titlebar_right_rl = (RelativeLayout) findViewById(R.id.titlebar_right_rl);
        titlebar_right_view = (ImageView) findViewById(R.id.titlebar_right_view);
        titlebar_right_tv = (TextView) findViewById(R.id.titlebar_right_tv);
        initBaseListener();
    }

    /**
     * 设置基类的监听事件
     */
    private void initBaseListener() {
        titlebar_back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClickListener();
            }
        });
        titlebar_right_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightClickListener(v);
            }
        });
    }

    /**
     * 点击actionbar的返回图片，结束当前activity。后面的子类如果需要改变事件，重写该方法
     */
    protected void onLeftClickListener() {
        finish();
    }

    /**
     * 点击actionbar的右边的图片或者文字的事件。后面的子类重写该方法定义点击事件
     */
    protected void onRightClickListener(View view) {
    }

    /**
     * 设置标题文字
     *
     * @param tittle
     */
    protected void setTittleText(String tittle) {
        titlebar_title_tv.setText(tittle);
    }

    /**
     * 设置标题右边的文字
     *
     * @param rightText
     */
    protected void setRightText(String rightText) {
        titlebar_right_rl.setVisibility(View.VISIBLE);
        titlebar_right_tv.setText(rightText);
    }

    /**
     * 隐藏标题栏
     */
    protected void hideTitleBar() {
        titleBar.setVisibility(View.GONE);
    }

    /**
     * 获取activity标题栏
     *
     * @return
     */
    public ViewGroup getTitleBar() {
        return titleBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mProgressDialog != null) {
//            if (mProgressDialog.isShowing()) {
//                mProgressDialog.dismiss();
//            }
//            mProgressDialog = null;
//        }
    }

    /**
     * -------------------------------------------隐藏和显示键盘----------------------------------------------
     */

    //隐藏虚拟键盘
    protected void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && this.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //显示虚拟键盘
    protected void ShowKeyboard(final EditText editText) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 1);

    }

    /**
     * -------------------------------------------显示加载对话框相关方法----------------------------------------------
     */


    public void showProgressDialog(String msg) {
        showLoadingDialog(msg);
    }

    public void showProgressDialog(int resId) {
        String msg = getResources().getString(resId);
        showLoadingDialog(msg);
    }

    public void showProgressDialog() {
        String msg = getResources().getString(R.string.loading);
        showLoadingDialog(msg);
    }


    private void showLoadingDialog(String msg) {
        if (mIsProgressDialogShow) {
            return;
        }
//        if (mProgressDialog == null) {
//            mProgressDialog = new CommitDataAnim(BaseActivity.this, msg);
//            mProgressDialog.setCanceledOnTouchOutside(false);
//            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    mIsProgressDialogShow = false;
//                }
//            });
//        }
//        mProgressDialog.show();
        mIsProgressDialogShow = true;
    }

    public void dismissProgressDialog() {
//        if (mIsProgressDialogShow && mProgressDialog != null) {
//            mProgressDialog.dismiss();
//            mIsProgressDialogShow = false;
//        }
    }


}

