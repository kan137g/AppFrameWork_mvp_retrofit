package com.kangwencai.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by   on 2015/10/30.
 *
 * 常用工具类
 */
public class CommonUtil {

    /**
     * 去除所有空格制表符 换行
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 是否是手机号
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhone(String phoneNumber) {
        Pattern p = Pattern.compile("^[1][3,5,7,8]\\d{9}");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 是否是座机
     * @param phoneNumber
     * @return
     */
    public static boolean isLandlinePhone(String phoneNumber){
        Pattern p = Pattern.compile("[0]{1}[0-9]{2,3}[0-9]{7,8}");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 是否是联系方式
     * @param phoneNumber
     * @return
     */
    public static boolean isContactWay(String phoneNumber){
        if(isPhone(phoneNumber)||isLandlinePhone(phoneNumber)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 匹配是否是中文
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(String chinese) {
        Pattern p = Pattern.compile("[\u4E00-\u9FFF]{2,10}");
        Matcher m = p.matcher(chinese);
        return m.matches();
    }

    /**
     * 判断email格式是否正确
     *
     * @param email 邮箱号码
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return email.matches(str);
    }

    /**
     * 验证是否为数字密码6到10位
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        Pattern p = Pattern.compile("[0-9]{6,10}");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    public static boolean getNetConnectState(Context context) {
        boolean netConnect = false;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo infoM = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (info.isConnected() || infoM.isConnected()) {
            netConnect = true;
        }
        return netConnect;
    }

    /**
     * 名称： 计算18位身份证的最后一位
     * 功能 : 根据前17位身份证号，求最后一位
     * 身份证最后一位的算法：
     * 1.将身份证号码的前17位的数字，分别乘以权数 ： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     *      (比如：第一位乘以7，第二位乘以9，以此类推)
     * 2.再将上面的所有乘积求和
     * 3.将求得的和mod以11（%11），得到一个小于11的数（0到11）
     * 4.然后从1 0 X 9 8 7 6 5 4 3 2几位校验码中找出最后一位的数字
     *   如果得到的是0，则对应第一位：1,如果得到的是1，则对应第二位：0
     *   如果得到的是2，则对应第三位：X,如果得到的是3，则对应第四位：9,以此类推
     * 5.最后得到的就是身份证的最后一位
     */
    public static Character getLastIDNum(String preIds) {
        Character lastId = null;
        //当传入的字符串没有17位的时候，则无法计算，直接返回
        if(preIds==null && preIds.length()<17) {
            return null;
        }
        int[] weightArray = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};//权数数组
        String vCode = "10X98765432";//校验码字符串
        int sumNum = 0;//前17为乘以权然后求和得到的数

        //循环乘以权，再求和
        for(int i=0;i<17;i++) {
            int index = Integer.parseInt(preIds.charAt(i) + "");
            sumNum = sumNum +index*weightArray[i];//乘以权数，再求和
        }

        int modNum = sumNum%11;//求模
        lastId = vCode.charAt(modNum);//从验证码中找出对应的数

        return lastId;
    }
    /**
     * 获取当前应用程序的版本名称
     */
    public static String getVersion(Context context) {
        String st = null;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

    /**
     * 获取当前应用程序的版本号
     */
    public static int getVersionCode(Context context) {
        int st = 1;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
            int version = packinfo.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }
}
