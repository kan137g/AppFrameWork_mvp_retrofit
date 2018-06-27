package com.kangwencai.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;


/**
 * 偏好设置的工具类
 */
public class PreferenceHelper {

    //用户名
    public static final String PHONE_NO = "phoneNo";
    //密码
    public static final String PASSWORD = "password";
    //手势密码
    public static final String LOCUS_PASSWORD = "locusPassword";

    public static final String LOGIN_INFO = "loginInfo";

    public static final String CUST_TYPE = "custTypeList";

    public static final String MARRIAGE_TYPE = "marriagetype";

    public static final String EDUCATION_TYPE = "educationtype";

    public static final String ACCOUNT_TYPE = "accounttype";

    public static final String INDUSTRY_TYPE = "industrytype";

    public static final String REPAY_TYPE = "repaytype";

    public static final String SEX_TYPE = "sextype";

    public static final String APPLY_TYPE = "applytype";

    public static final String FEEDBACK_TYPE = "feedBackType";

    public static final String APP_SHARE_URL = "appShareUrl";
    //是否已设置手势密码
    public static final String IS_LOCUS_PASSWORD = "isLocusPassWord";

    public static final String VERSION_CODE = "versionCode";

    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    public static SharedPreferences getInstance(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();
        return settings;
    }

    public static boolean getBoolean(String key, boolean defValue) {

        return settings.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defValue) {
        return settings.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static long getLong(String key, long defValue) {
        return settings.getLong(key, defValue);
    }

    public static void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static String getString(String key, String defValue) {
        return settings.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public static void registerOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
