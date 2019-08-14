package com.vanke.commonlib.net.rest;


import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import com.orhanobut.logger.Logger;
import com.vanke.commonlib.widget.loadingview.CommitDataAnim;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kangwencai on 2016/11/10.
 */
public abstract class RestCallBack<T extends ApiResponse> implements Callback<T> {
    /*系统错误*/
    private static final String SYS_ERROR = "SYS_ERROR";
    /*请求成功*/
    private static final String OK = "ok";

    //    弱引用反正内存泄露
    private WeakReference<Context> mActivity;
    //    当前是否正在显示
    private boolean mIsProgressDialogShow;
    //    加载框可自己定义
    private CommitDataAnim mProgressDialog;

    public RestCallBack() {


    }

    public RestCallBack(Context context, int resId) {

        this.mActivity = new WeakReference<>(context);

        showProgressDialog(resId);
    }

    public RestCallBack(Context context, String msg) {

        this.mActivity = new WeakReference<>(context);

        showProgressDialog(msg);
    }

    /**
     * 请求成功，且返回正确数据
     *
     * @param result
     */
    public abstract void onSuccess(JSONObject result);

    /**
     * 请求失败
     *
     * @param msg
     */
    public abstract void onFailure(String msg);

    /**
     * 请求失败，可以考虑是否再次请求
     */
    public abstract void requestAgain();


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        ApiResponse apiResponse = response.body();
        if (null == apiResponse) {
            onFailure("请求失败");
            return;
        }
        Logger.e("本次收到的数据" + apiResponse.toString());
        String code = apiResponse.getCode();
        String msg = apiResponse.getMessage();

        onSuccess(apiResponse.getData());

        dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dismissProgressDialog();
        requestAgain();

        Logger.e(t.toString());
    }

    /**
     * 根据字符串来显示
     *
     * @param msg
     */
    public void showProgressDialog(String msg) {
        if (TextUtils.isEmpty(msg) || msg == null) {
            return;
        }
        showLoadingDialog(msg);
    }

    /**
     * 根据字符串引索值来显示
     *
     * @param resId
     */
    public void showProgressDialog(int resId) {
        if (resId <= 0) {
            return;
        }
        Context context = mActivity.get();
        String msg = context.getResources().getString(resId);
        showLoadingDialog(msg);
    }

    public void showProgressDialog() {
        Context context = mActivity.get();
         String msg = "loading";
         showLoadingDialog(msg);
    }

    /**
     * 加载动画
     *
     * @param msg
     */
    private void showLoadingDialog(String msg) {
        try {
            if (mIsProgressDialogShow) {
                return;
            }
            if (mProgressDialog == null) {
                mProgressDialog = new CommitDataAnim(mActivity.get(), msg);
                //点击外面是否取消
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mIsProgressDialogShow = false;
                    }
                });
            } else {
                mProgressDialog.setContent(msg);
            }

            mProgressDialog.show();
            mIsProgressDialogShow = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dismissProgressDialog() {
        if (mIsProgressDialogShow && mProgressDialog != null) {
            mProgressDialog.dismiss();
            mIsProgressDialogShow = false;
        }
    }


}