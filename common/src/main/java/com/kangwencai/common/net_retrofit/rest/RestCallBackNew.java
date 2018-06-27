package com.kangwencai.common.net_retrofit.rest;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.kangwencai.common.R;
import com.kangwencai.common.utils.LogUtils;
import com.kangwencai.common.utils.ToastUtils;
import com.kangwencai.common.widget.loadingview.CommitDataAnim;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: retrofit的数据请求回掉，返回的数据的主体封装为指定的范型对象
 * <p>
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public abstract class RestCallBackNew<T> implements Callback<ApiResponse> {
    /*系统错误*/
    private static final String SYS_ERROR = "SYS_ERROR";
    /*禁止访问*/
    public static final String ACCESS_FORBIDDEN = "ACCESS_FORBIDDEN";
    //会话过期
    public static final String SESSION_TIMEOUT = "SESSION_TIMEOUT";
    /*请求成功*/
    private static final String OK = "ok";
    public static final String NET_ERROR = "网络异常";
    /**
     * 是否显示错误信息提示，在使用记录的账号密码登录的时候，如果登录失败不应该有提示
     */
    private boolean isShowErrorMsg = true;
    /*定义一个静态常量专门来解析gson数据*/
    public static final Gson gson = new Gson();


    //    弱引用反正内存泄露
    private WeakReference<Context> mActivity;
    //    当前是否正在显示
    private boolean mIsProgressDialogShow;
    //    加载框可自己定义
    private CommitDataAnim mProgressDialog;

    public RestCallBackNew() {
    }

    public RestCallBackNew(boolean isShowErrorMsg) {
        this.isShowErrorMsg = isShowErrorMsg;
    }

    public RestCallBackNew(Context context, int resId) {
        this.mActivity = new WeakReference<>(context);
        showProgressDialog(resId);
    }

    public RestCallBackNew(Context context, String msg) {
        this.mActivity = new WeakReference<>(context);
        showProgressDialog(msg);
    }


    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        try {
            dismissProgressDialog();
            ApiResponse apiResponse = response.body();
            if (null == apiResponse) {
                onFailure("请求失败");
                return;
            }
            LogUtils.e("本次收到的数据" + apiResponse.toString());
            String code = apiResponse.getCode();
            String msg = apiResponse.getSummary();

            if (OK.equalsIgnoreCase(code)) {
                if (null != apiResponse.getResult()) {
                    T object = gson.fromJson(apiResponse.getResult(), getTClass());
                    onSuccess(object);
                } else {
                    onSuccess(null);
                }

            } else {
                //允许显示错误信息，且错误信息不是系统错误的时候显示错误信息
                if (isShowErrorMsg) {
//                    ToastUtils.toast(msg);
                }
                //
                if (SESSION_TIMEOUT.equalsIgnoreCase(code)) {
//                    Toast.makeText(MyApplication.getInstance(),"登陆已超时，请您重新登陆",Toast.LENGTH_LONG).show();

                } else {
                    onFailure(msg);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        dismissProgressDialog();
//        ToastUtils.toast("网络异常，请重新操作");
        onNetError(NET_ERROR);

        LogUtils.e(t.toString());
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
        String msg = context.getResources().getString(R.string.loading);
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

    /**
     * 获取范型对象T的T.class
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    /**
     * 请求成功，且返回正确数据
     *
     * @param result
     */
    public abstract void onSuccess(T result);


    /**
     * 请求失败
     *
     * @param msg
     */
    public abstract void onFailure(String msg);

    /**
     * 请求失败，可以考虑是否再次请求
     */
    public abstract void onNetError(String msg);
}