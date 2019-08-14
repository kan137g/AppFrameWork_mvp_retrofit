package com.vanke.handlecashregister.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/12
 */
public class DateFormatUtils {


    /**
     * @return 返回格式 2019-8-12
     */
    public static String formatDate() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(new Date());
    }

    /**
     * @return 2019-08-12 15:24:21
     */
    public static String formatDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * @return 20190812152421
     */
    public static String formatDateTime2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static String formatDateTime2(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static String formatDateTime(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(date));
    }

    public static void main(String[] args) {
        System.out.println(formatDate());
        System.out.println(formatDateTime());
        System.out.println(formatDateTime2());
    }


}
