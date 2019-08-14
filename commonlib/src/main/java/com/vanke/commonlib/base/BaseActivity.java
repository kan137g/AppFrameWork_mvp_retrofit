package com.vanke.commonlib.base;


import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.vanke.commonlib.R;


/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {
    protected String TAG = getClass().getSimpleName();
    protected BaseActivity mAct;
    protected int deviceWidth;
    protected int deviceHeight;

    Toolbar mToolbar;

    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mAct = this;
        deviceHeight = getWindowManager().getDefaultDisplay().getHeight();
        deviceWidth = getWindowManager().getDefaultDisplay().getWidth();


        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        rootView = findViewById(R.id.view_root);


        fillView();
        initViewFromXML();
        initData();
        addBaseListener();
    }


    public void setContent(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, rootView);
    }

    protected void addBaseListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void hideTittle() {
        mToolbar.setVisibility(View.GONE);
    }

    protected void hideNavigationIcon() {
        mToolbar.setNavigationIcon(R.color.transparent);
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
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);

    }


}

