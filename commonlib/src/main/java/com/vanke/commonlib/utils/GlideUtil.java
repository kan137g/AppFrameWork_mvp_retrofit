package com.vanke.commonlib.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.vanke.commonlib.InitTools;
import com.vanke.commonlib.R;

/**
 * Description: glide 工具类
 * // Glide.with(context).load(R.mipmap.ic_launcher).into(imageView);
 * //Glide.with(context).load(file).into(imageView);
 * //Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
 * //Glide.with(context).load("Android.resource://com.frank.glide/raw/raw_1").into(imageView);
 * //Glide.with(context).load("android.resource://com.frank.glide/raw/"+R.raw.raw_1).into(imageView);
 * //Glide.with(context).load("content://media/external/images/media/139469").into(imageView);
 * //Glide.with(context).load("file:///android_asset/f003.gif").into(imageView);
 * //Glide.with(context).load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg").into(imageView);
 * Author: kangwencai
 * DATA: Date on 2019-08-14.
 * PS: Not easy to write code, please indicate.
 */
public class GlideUtil {


    private static RequestManager initGlide(Context context) {


        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .centerCrop();
        return Glide.with(context).setDefaultRequestOptions(options);


    }

    /**
     * 加载网络图片
     */
    public static void loadUrl(String url, ImageView imageView) {

        try {

            initGlide(InitTools.app).load(url).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载asset图片
     */
    public static void loadAssetImg(String path, ImageView imageView) {
        try {
            initGlide(InitTools.app).load("file:///android_asset/" + path).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载本地图片
     */
    public static void loadLocalImg(String path, ImageView imageView) {
        try {
            initGlide(InitTools.app).load("file://" + Environment.getExternalStorageDirectory().getPath() + "/" + path).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
