package com.kangwencai.common;

import android.os.Environment;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public interface IConstants {
    /**
     * 标示是否是 debug 版本
     */
    boolean isDebug = true;
    String baseUrl = "http://news-at.zhihu.com/api/4/";


    String netErrorTips = "网络请求异常";

    // 外部存储设备的根路径
    String ExternalStorageRootPath = Environment.getExternalStorageDirectory().getPath();
    // app目录根路径
    String BasePath = ExternalStorageRootPath + "/ExpressGold/";

    // 文件存放路径
    String FileCachePath = BasePath + "/FileCache/";


//    //外部缓存路径,会随着程序卸载而删除
//    String ExternalCashPath = MyApplication.getInstance().getExternalCacheDir().getParent();
//    //日志的路径
//    String LogPath = ExternalCashPath + "/log/";
//    // 保存图片
//    String ImageCachePath = ExternalCashPath + "/ImageCache/";
//    // 下载存储地址
//    String DOWNLOADPath = ExternalCashPath + "/DOWNLOAD/";

}
