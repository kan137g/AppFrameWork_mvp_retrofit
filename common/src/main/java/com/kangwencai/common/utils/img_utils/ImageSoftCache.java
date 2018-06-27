package com.kangwencai.common.utils.img_utils;

import android.content.Context;
import android.graphics.Bitmap;



import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/7/13.
 * 图片的软引用
 */
public class ImageSoftCache {
    private static ImageSoftCache myImgCache;
//    private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
//    private HashMap<String, SoftReference<Bitmap>> imageCacheThumbnail = new HashMap<String, SoftReference<Bitmap>>();


    /**
     * 用于Chche内容的存储
     */

    private HashMap<String, MySoftRef> imageCache;
    private HashMap<String, MySoftRef> imageCacheThumbnail;


    /**
     * 垃圾Reference的队列（所引用的对象已经被回收，则将该引用存入队列中）
     */
    private ReferenceQueue<Bitmap> q;


    /**
     * 继承SoftReference，使得每一个实例都具有可识别的标识。
     */
    private class MySoftRef extends SoftReference<Bitmap> {
        private String _key = null;

        public MySoftRef(Bitmap bmp, ReferenceQueue<Bitmap> q, String key) {
            super(bmp, q);
            _key = key;
        }
    }


    private ImageSoftCache() {
        imageCache = new HashMap<String, MySoftRef>();
        imageCacheThumbnail = new HashMap<String, MySoftRef>();
        q = new ReferenceQueue<Bitmap>();
    }

    /**
     * 取得缓存器实例
     */
    public static ImageSoftCache getInstance() {
        if (myImgCache == null) {
            myImgCache = new ImageSoftCache();
        }
        return myImgCache;
    }

    public HashMap<String, MySoftRef> getImageCache() {
        return imageCache;
    }

    /**
     * 从缓存中获取图片
     *
     * @param filePath 图片路径
     * @return
     */
    public Bitmap getCacheBitMap(String filePath) {
        Bitmap bitmap = null;
        if (imageCache.containsKey(filePath)) {
            bitmap = imageCache.get(filePath).get();
        }
        if (bitmap == null) {
            bitmap = ImageTool.compressImageFromFile(filePath);
            putBitMapCache(filePath, bitmap);
        }

        return bitmap;
    }

    public Bitmap getCacheBitMapThumbnail(Context context,String filePath) {
//        SoftReference<Bitmap> result = null;
        Bitmap bitmap = null;
        if (imageCacheThumbnail.containsKey(filePath)) {
            bitmap = imageCacheThumbnail.get(filePath).get();
        }
        if (bitmap == null) {
            bitmap = ImageTool.getImageThumbnail(context, filePath);
            putBitMapCacheThumbnail(filePath, bitmap);
        }

        //针对照相和没有及时生成缩略图的情况
        if (bitmap == null) {
            bitmap = getCacheBitMap(filePath);
        }

        return bitmap;
    }


    /**
     * 将bitmap放入到缓存方便引用和内存的回收
     *
     * @param filePath 图片的路径
     * @param bitmap   bitmap的引用
     * @return 插入的条数
     */
    public int putBitMapCache(String filePath, Bitmap bitmap) {
        cleanCache();
        int size = imageCache.size();
        MySoftRef ref = new MySoftRef(bitmap, q, filePath);
        imageCache.put(filePath, ref);
        return imageCache.size() - size;
    }

    public int putBitMapCacheThumbnail(String filePath, Bitmap bitmap) {
        cleanCache();
        int size = imageCacheThumbnail.size();
        MySoftRef ref = new MySoftRef(bitmap, q, filePath);
        imageCacheThumbnail.put(filePath, ref);
//        imageCacheThumbnail.put(filePath, bitmap);
        return imageCacheThumbnail.size() - size;
    }

    private void cleanCache() {
        MySoftRef ref = null;
        while ((ref = (MySoftRef) q.poll()) != null) {
            imageCache.remove(ref._key);
            imageCacheThumbnail.remove(ref._key);
        }
    }


    /**
     * 清除Cache内的全部内容
     */

    public void clearCache() {
        cleanCache();
        imageCache.clear();
        System.gc();
        System.runFinalization();

    }

}
