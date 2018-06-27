package com.kangwencai.common.widget.loadingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangwencai.common.R;


/**
 * Created by kangwencai on 2016/11/10.
 *
 * 自定义加载view,可以在做网络请求的时候调用,因为时view,可以在指定布局中显示
 */
 public class RefreshPageProgressView extends FrameLayout {
    /**
     * 属性
     */
    private AttributeSet attrs;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 动画
     */
    private AnimationDrawable mAnimation;
    /**
     * iv
     */
    private ImageView loadingIv;
    /**
     * tv
     */
    private TextView loadingTv;
    /**
     * 加载的文字
     */
    private String mLoadText;



    public RefreshPageProgressView(Context context) {
        super(context);
        this.context = context;
        initView();
    }
    public RefreshPageProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initView();
    }
    public RefreshPageProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        initView();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshPageProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        this.attrs = attrs;
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {


        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_loading_animation, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        loadingIv = (ImageView) view.findViewById(R.id.loadingIv);
        loadingTv = (TextView) view.findViewById(R.id.loadingTv);
        loadingTv.setTextColor(context.getResources().getColor(R.color.gray_66));
        addView(view, layoutParams);
        setVisibility(View.GONE);
    }


    /**
     * 设置加载的文字
     * @param loadingText
     */
    public void setLoadingText(CharSequence loadingText) {
        loadingTv.setText(loadingText);
    }


    /**
     * 开始加载
     */
    private void startLoading() {
        if (mAnimation != null && mAnimation.isRunning()) {
            return;
        }
        this.removeCallbacks(mAnimationRunnable);
        this.post(mAnimationRunnable);

    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        if (mAnimation != null) {
            if (mAnimation.isRunning()) {
                mAnimation.stop();
            }
            mAnimation = null;
        }
        this.removeCallbacks(mAnimationRunnable);
    }

    /**
     * 播放动画的runable
     */
    private Runnable mAnimationRunnable = new Runnable() {
        @Override
        public void run() {
            if(mAnimation == null) {
                //获取动画资源
                mAnimation = (AnimationDrawable)loadingIv.getBackground();
            }
            mAnimation.start();
        }
    };

    /**
     * 设置显示隐藏的属性
     * @param visibility
     */
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            startLoading();
        } else {
            stopLoading();
        }
    }

    /**
     * 显示自定义view
     */
    public void show(){
        setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏自定义view
     */
    public void dismiss(){
        setVisibility(View.GONE);
    }
}
