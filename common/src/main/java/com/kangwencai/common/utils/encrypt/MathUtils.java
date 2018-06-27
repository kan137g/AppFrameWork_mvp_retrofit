package com.kangwencai.common.utils.encrypt;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by b3a4a on 2015/10/30.
 * 涉及到计算的工具类
 */
public class MathUtils {
    private static final int DEF_DIV_SCALE = 10;

    /**
     * * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double add(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }

    /**
     * * 两个Double数相减 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double sub(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.subtract(b2).doubleValue());
    }

    /**
     * * 两个Double数相乘 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double mul(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.multiply(b2).doubleValue());
    }

    /**
     * * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double div(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
                .doubleValue());
    }

    /**
     * 给.开始的金额添加0
     *
     * @param money
     * @return
     */
    public static String addZero(String money) {
        if (money.startsWith(".")) {
            return "0" + money;
        }
        return money;
    }

    /**
     * * 两个Double数相除，并保留scale位小数 *
     *
     * @param v1    *
     * @param v2    *
     * @param scale *
     * @return Double
     */
    public static Double div(Double v1, Double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }


    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据经纬度计算位置
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 在字符串后面加上秒级的时间戳，再用SHA1签名
     *
     * @param originalStr 原文
     * @return 签名后的密文
     */
    public static String encryptBySHA1(String originalStr) {
        try {
            java.security.MessageDigest alga = java.security.MessageDigest
                    .getInstance("SHA-1");
            long date = System.currentTimeMillis() / 1000;
            String myinfo = originalStr + date;
            alga.update(myinfo.getBytes());
            byte[] digesta = alga.digest();
            System.out.println("明文:" + myinfo);
            System.out.println("密文：" + printFrame(digesta));
            return printFrame(digesta);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 打印一个数据包
     *
     * @param bs
     */
    public static String printFrame(byte[] bs) {
        StringBuilder builder = new StringBuilder();
        String str;
        for (byte b : bs) {
            str = Integer.toHexString(b & 0xff);
            if (str.length() != 2) {
                builder.append("0" + str);
            } else {
                builder.append(str);
            }
        }
        return builder.toString();

    }

    /**
     * 随机一个最大值为max的随机数
     *
     * @param max
     * @return
     */
    public static int randomInteger(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * 千分位
     * @param data
     * @return
     */
    public static String formatToSeparate(String data) {
        try {
            Float aFloat=Float.parseFloat(data);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            return df.format(aFloat);
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }

    }

    /**
     * 千分位
     * @param data
     * @return
     */
    public static String formatToSeparate(float data) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(data);
    }
    /**
     * 千分位
     * @param data
     * @return
     */
    public static String formatToSeparate(double data) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(data);
    }

    /****
     * 随机一个长度为length的字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(numbersAndLetters[randGen.nextInt(71)]);
        }
        return sb.toString();
    }

}
