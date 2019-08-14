package com.vanke.commonlib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;


import com.vanke.commonlib.InitTools;


/**
 * 偏好设置的工具类
 */
public class PreferenceHelper {

    public static final String SP_NAME = "SP_HANDLE_CASH";


    //用户名
    public static final String PHONE_NO = "phoneNo";
    //密码
    public static final String PASSWORD = "password";


    public static boolean getBoolean(String key, boolean defValue) {

        SharedPreferences settings = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        return settings.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences settings = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static long getLong(String key, long defValue) {
        SharedPreferences settings = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static String getString(String key, String defValue) {
        SharedPreferences settings = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void remove(String key) {
        SharedPreferences.Editor editor = InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }

    public static void registerOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
            InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
            InitTools.app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).unregisterOnSharedPreferenceChangeListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
