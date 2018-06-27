package com.kangwencai.common.model.listener;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public interface INetCallBack<T> {

    void onSuccess(T response);

    void onFail(String errMsg);

    void onNetError(String errMsg);
}
