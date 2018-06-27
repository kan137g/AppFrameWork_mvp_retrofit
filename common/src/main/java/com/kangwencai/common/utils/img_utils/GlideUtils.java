package com.kangwencai.common.utils.img_utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kangwencai.common.R;

/**
 * Description: 参考https://www.aliyun.com/jiaocheng/5766.html
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public class GlideUtils {
    static RequestOptions requestOptions = new RequestOptions();

    static {
        //        占位符
        requestOptions.placeholder(R.drawable.back);// 设置占位图
        requestOptions.error(R.drawable.back);// 设置错误图片
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);// 缓存图片的方式
        requestOptions.dontAnimate();
        //        转换成圆角图片
//        requestOptions.transform(new RoundedCorners(20));

    }

    /**
     * (1)
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, String url, ImageView imgeview) {
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imgeview);

    }

    /**
     * （2）
     * 获取到Bitmap---不设置错误图片，错误图片不显示
     *
     * @param context
     * @param url
     * @param imageView
     */

    public static void showImageViewGone(Context context, String url, ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .apply(requestOptions)
//                .into(imgeview);

        //        Glide.with(context)
//                .load(url)
//                .apply(requestOptions)
//                .into(imgeview);
//                 .into(new SimpleTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        imageView.setVisibility(View.VISIBLE);
//                        BitmapDrawable bd = new BitmapDrawable(resource);
//                        imageView.setImageDrawable(bd);
//                    }
//
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        imageView.setVisibility(View.GONE);
//                    }
//                });

    }

    /**
     * （3）
     * 设置RelativeLayout
     * <p>
     * 获取到Bitmap
     *
     * @param context
     * @param errorimg
     * @param url
     * @param bgLayout
     */

    public static void showImageView(Context context, int errorimg, String url,
                                     final RelativeLayout bgLayout) {

//                Glide.with(context)
//                .load(url)
//                .apply(requestOptions)
//                .into(new SimpleTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        BitmapDrawable bd = new BitmapDrawable(resource);
//
//                        bgLayout.setBackground(bd);
//                    }
//
//
//                });

    }


    /**
     * （6）
     * 获取到Bitmap 高斯模糊         RelativeLayout
     *
     * @param context
     * @param errorimg
     * @param url
     * @param bgLayout
     */

    public static void showImageViewBlur(Context context, int errorimg,
                                         String url, final RelativeLayout bgLayout) {
//        Glide.with(context).load(url) .error(errorimg)
//                // 设置错误图片
//
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                // 缓存修改过的图片
//                .placeholder(errorimg)
//                .transform(new BlurTransformation(context))// 高斯模糊处理
//                // 设置占位图
//
//                .into(new SimpleTarget<Bitmap>() {
//
//                    @SuppressLint("NewApi")
//                    @Override
//                    public void onResourceReady(Bitmap loadedImage,
//                                                GlideAnimation<? super Bitmap> arg1) {
//                        BitmapDrawable bd = new BitmapDrawable(loadedImage);
//
//                        bgLayout.setBackground(bd);
//
//                    }
//
//                    @Override
//                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                        // TODO Auto-generated method stub
//                        super.onLoadFailed(e, errorDrawable);
//
//                        bgLayout.setBackgroundDrawable(errorDrawable);
//                    }
//
//                });

    }

    /**
     * （7）
     * 获取到Bitmap 高斯模糊 LinearLayout
     *
     * @param context
     * @param errorimg
     * @param url
     * @param bgLayout
     */

    public static void showImageViewBlur(Context context, int errorimg,
                                         String url, final LinearLayout bgLayout) {
//        Glide.with(context).load(url).asBitmap().error(errorimg)
//                // 设置错误图片
//
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                // 缓存修改过的图片
//                .placeholder(errorimg)
//                .transform(new BlurTransformation(context))// 高斯模糊处理
//                // 设置占位图
//
//                .into(new SimpleTarget<Bitmap>() {
//
//                    @SuppressLint("NewApi")
//                    @Override
//                    public void onResourceReady(Bitmap loadedImage,
//                                                GlideAnimation<? super Bitmap> arg1) {
//                        BitmapDrawable bd = new BitmapDrawable(loadedImage);
//
//                        bgLayout.setBackground(bd);
//
//                    }
//
//                    @Override
//                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                        // TODO Auto-generated method stub
//                        super.onLoadFailed(e, errorDrawable);
//
//                        bgLayout.setBackgroundDrawable(errorDrawable);
//                    }
//
//                });

    }

    /**
     * （8）
     * 显示图片 圆角显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Application context, int errorimg,
                                             String url, ImageView imgeview) {
        //圆角
        requestOptions.transform(new RoundedCorners(20));

        Glide.with(context).load(url)
                .apply(requestOptions)
                .into(imgeview);

    }


}
