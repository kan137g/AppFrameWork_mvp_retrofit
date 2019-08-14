package com.vanke.handlecashregister.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.vanke.commonlib.base.BaseActivity;
import com.vanke.commonlib.net.rest.ApiResponse;
import com.vanke.commonlib.net.rest.RestCallBack;
import com.vanke.commonlib.net.service.MyRequestBodyCreator;
import com.vanke.handlecashregister.R;
import com.vanke.handlecashregister.net.CreateRequestData;
import com.vanke.handlecashregister.net.RetrofitRest;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    @butterknife.BindView(R.id.et_username)
    EditText etUsername;
    @butterknife.BindView(R.id.et_password)
    EditText etPassword;


    @Override
    public void fillView() {
        setContent(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    public void initViewFromXML() {


//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        };
//        etUsername.addTextChangedListener(afterTextChangedListener);
//        etPassword.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(usernameEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });


    }

    @Override
    public void initData() {
        RetrofitRest.getInstance().begin(MyRequestBodyCreator.create(CreateRequestData.getBeginParams().toJSONString()))
                .enqueue(new RestCallBack<ApiResponse>() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void requestAgain() {

                    }
                });
    }


    @butterknife.OnClick(R.id.btn_login)
    public void onViewClicked() {

        Log.e(TAG, "~~~~~~~~~~~~~~~");
        Logger.e("??????????????????????");
        RetrofitRest.getInstance().begin(MyRequestBodyCreator.create(CreateRequestData.getBeginParams().toJSONString()))
                .enqueue(new RestCallBack<ApiResponse>() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void requestAgain() {

                    }
                });
    }
}
