package com.vanke.handlecashregister.utils;

import java.util.Random;

/**
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/13
 */
public class OutTradeNo {

    public static String createTradNo(long date,String orgNo) {

         String result=DateFormatUtils.formatDateTime2(date) + orgNo + createRandomNo(4);
        System.out.println("订单号是："+result);
        return result;
    }

    /**
     * 生成固定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String createRandomNo(int length) {
        String temp = "000000000000000000000000000000000000000";
        int random = new Random().nextInt((int) Math.pow(10, length));

        temp += random;

        return temp.substring(temp.length() - length, temp.length());

    }
}
