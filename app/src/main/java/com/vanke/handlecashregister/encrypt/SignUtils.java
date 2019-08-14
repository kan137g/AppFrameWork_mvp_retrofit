package com.vanke.handlecashregister.encrypt;

import com.alibaba.fastjson.JSON;

/**
 * Description: 签名算法
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/12
 */
public class SignUtils {

    private static final String key = "123456789";

    public static <T> Request<T> sign(T paramT) {
        Request localRequest = new Request(paramT);
        long l = System.currentTimeMillis() / 1000L;
        String str = "";
        if (localRequest.data != null)
            str = JSON.toJSONString(paramT);
        localRequest.timestamp = l;
        localRequest.sign = MD5.sign(str, key, l);
        return localRequest;
    }

}
