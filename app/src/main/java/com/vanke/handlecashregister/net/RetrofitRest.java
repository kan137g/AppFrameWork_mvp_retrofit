package com.vanke.handlecashregister.net;

import com.vanke.commonlib.net.RetrofitUtil;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2019-08-14.
 * PS: Not easy to write code, please indicate.
 */
public class RetrofitRest {

    static ApiService apiService;

    public static ApiService getInstance() {
        if (apiService == null) {
            apiService = RetrofitUtil.getInstance().create(ApiService.class);

        }
        return apiService;
    }
}
