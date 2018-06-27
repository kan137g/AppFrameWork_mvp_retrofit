package com.kangwencai.common.utils.img_utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.kangwencai.common.IConstants;
import com.kangwencai.common.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * 获取缩略图或图片
 */
public class ImageTool {
    /**
     * 获取图片缩略图
     *
     * @param context
     * @param ImagePath 图片路径
     * @return
     */
    public static Bitmap getImageThumbnail(Context context, String ImagePath) {
        ContentResolver testcr = context.getContentResolver();
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,};
        String whereClause = MediaStore.Images.Media.DATA + " = '" + ImagePath + "'";
        Cursor cursor = testcr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, whereClause,
                null, null);
        int _id = 0;
        String imagePath = "";
        //虽然没数据这地方还是要关闭....
        if (cursor == null || cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        if (cursor.moveToFirst()) {
            int _idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            int _dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                _id = cursor.getInt(_idColumn);
                imagePath = cursor.getString(_dataColumn);
                LogUtils.e(_id + " path:" + imagePath + "---");
            } while (cursor.moveToNext());
        }
        cursor.close();
        LogUtils.e("数据库是否关闭" + cursor.isClosed());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(testcr, _id, MediaStore.Images.Thumbnails.MINI_KIND,
                options);

        return bitmap;
    }


    /**
     * 获取视频缩略图
     *
     * @param videoPath
     * @param width
     * @param height
     * @param kind
     * @return
     */
    private Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 从指定路径读取图片并进行缩放
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressImageFromFile(String srcPath) {

        Bitmap bitmap;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = decodeFile(srcPath, newOpts);
        //调用了这个方法进行二次压缩,这里又个疑问是压缩过程是需要时间，不压缩需要空间，亲自测试结果发现，在时间和空间上都得到了提升
        return compressImageSize(bitmap);
        //觉得质量下降了可以，直接拿解析到的图片
//        return bitmap;
    }


    /***
     * 根据配置解析图片
     *
     * @param srcPath
     * @param newOpts
     * @return
     */
    public static Bitmap decodeFile(String srcPath, BitmapFactory.Options newOpts) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new FileInputStream(srcPath);
            bitmap = BitmapFactory.decodeStream(inputStream, null,
                    newOpts);
            inputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.e("该路径下的图片未找到");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap getScaleBitmap(String filename, int viewWidth,
                                        int viewHeight) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);
        options.inSampleSize = getSimpleSize(options, viewWidth, viewHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);

    }

    public static int getSimpleSize(BitmapFactory.Options options,
                                    int viewWidth, int viewHeight) {
        int simpleSize = 1;
        int imgHeight = options.outHeight;
        int imgWidth = options.outWidth;

        int widthRatio = (int) Math.ceil(imgWidth / viewWidth);
        int heightRatio = (int) Math.ceil(imgHeight / viewHeight);
        if (widthRatio > 1 && heightRatio > 1) {
            if (widthRatio > heightRatio) {
                simpleSize = widthRatio;
            } else {
                simpleSize = heightRatio;
            }
        }
        return simpleSize;
    }


    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        if (matrix != null) {
            // 创建新的图片
            try {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                        (int) (bitmap.getWidth()), (int) (bitmap.getHeight()),
                        matrix, true);
            } catch (OutOfMemoryError err) {
                err.printStackTrace();
            }
        }
        return bitmap;
    }


    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            left = 0;
            top = 0;
            right = width;
            bottom = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);// 设置画笔无锯齿

        canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas
        paint.setColor(color);

        // 以下有两种方法画圆,drawRounRect和drawCircle
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
        canvas.drawBitmap(bitmap, src, dst, paint); //以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

        return output;
    }


    /**
     * 图片转为灰色
     *
     * @param bmSrc
     * @return
     */
    public static Bitmap bitmap2Gray(Bitmap bmSrc) {
        int width, height;
        height = bmSrc.getHeight();
        width = bmSrc.getWidth();
        Bitmap bmpGray = null;
        bmpGray = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGray);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmSrc, 0, 0, paint);

        return bmpGray;
    }


    /**
     * 创建一个可以用来存放图片的文件,如果挂在了不可移除的scared则放到，sd卡，否则放到data/包名称/data目录下
     *
     * @param context
     * @return
     */
    public static File createTmpFile(Context context) {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            // 已挂载，放在手机的Picture目录下，不会因为软件卸载而删除
//            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//            这个目录下的文件会随着卸载而卸载

            File pic = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (pic == null) {

                pic = new File(context.getExternalCacheDir().getParent() + "/Picture");
                pic.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_" + timeStamp + "";
            File tmpFile = new File(pic, fileName + ".jpg");
            return tmpFile;
        } else {
            File cacheDir = context.getCacheDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_" + timeStamp + "";
            File tmpFile = new File(cacheDir, fileName + ".jpg");
            return tmpFile;
        }

    }

    /**
     * 将bitmap 保存在文件中
     *
     * @param mBitmap 要保存的Bitmap
     * @param file    要保存的文件
     */
    public static void saveBitmap(Bitmap mBitmap, File file) {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        if (file == null) {
            return;
        }
        try {
            baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bitmapData = baos.toByteArray();
            fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param pathList
     */
    public static ArrayList<String> compressImage(Context context, ArrayList<String> pathList) {
        if (pathList == null || pathList.size() == 0) return pathList;

        //压缩图片的路径
        ArrayList<String> compressList = new ArrayList<>();
        //压缩图片保存的，目的路径地址
        String objPath;
        //文件名
        String fileName;

        for (String srcPath : pathList) {
            fileName = srcPath.substring(srcPath.lastIndexOf("/") + 1, srcPath.lastIndexOf(".")) + ".jpg";
            String imageCachePath = context.getExternalCacheDir().getParent() + "/ImageCache/";
            objPath = imageCachePath + fileName;

            Log.e("compress", "压缩的路径是：" + objPath);
            //压缩成功
            if (ImageTool.compressImageAndSave(srcPath, imageCachePath)) {
                compressList.add(objPath);

                //压缩失败，失败的原因只有一个，就是压缩的路径的的sdcard挂载和访问出了问题，这个时候上传原图
            } else {
                compressList.add(srcPath);
            }
        }
        return compressList;
    }

    /**
     * 压缩srcPath文件然后保存到objPath路径下
     *
     * @param srcPath 原文件路径
     * @param objPath 目标文件路径
     * @return
     */
    public static boolean compressImageAndSave(String srcPath, String objPath) {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
//        LogUtils.e("源文件的文件名为"+srcPath);
        Bitmap mBitmap = ImageSoftCache.getInstance().getCacheBitMap(srcPath);
        File file = new File(objPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = srcPath.substring(srcPath.lastIndexOf("/"), srcPath.lastIndexOf(".")) + ".jpg";
        file = new File(objPath, fileName);

        try {
            //如果存在则先删除
            file.deleteOnExit();

            file.createNewFile();
//            LogUtils.e("目标文件的文件名为"+file.getAbsolutePath());

            baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bitmapData = baos.toByteArray();
            fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * @param srcPath
     * @param objPath
     * @return
     */
    public static boolean compressImageAndSaveByFullPath(String srcPath, String objPath) {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
//        LogUtils.e("源文件的文件名为"+srcPath);
        Bitmap mBitmap = ImageSoftCache.getInstance().getCacheBitMap(srcPath);
        File file = new File(objPath);

        try {
            //如果存在则先删除
            file.deleteOnExit();

            file.createNewFile();
//            LogUtils.e("目标文件的文件名为"+file.getAbsolutePath());

            baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bitmapData = baos.toByteArray();
            fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 压缩srcPath文件然后保存到objPath路径下
     *
     * @param srcPath 原文件路径
     * @param context
     * @return
     */
    public static String compressImageAndSave(String srcPath, Context context) {

        FileOutputStream fos = null;
        File file = createTmpFile(context);
        try {

            byte[] bitmapData = compressImageFromFileToByteArray(srcPath);
            fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }


    /**
     * 从指定路径读取图片并进行缩放
     *
     * @param srcPath
     * @return
     */
    public static byte[] compressImageFromFileToByteArray(String srcPath) {

        Bitmap bitmap;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = decodeFile(srcPath, newOpts);
        //调用了这个方法进行二次压缩,这里又个疑问是压缩过程是需要时间，不压缩需要空间，亲自测试结果发现，在时间和空间上都得到了提升
        return compressImageSizeToByteArray(bitmap);

    }

    /**
     * 方法概述：图片质量压缩
     */
    protected static byte[] compressImageSizeToByteArray(Bitmap image) {
        if (image == null)
            return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里10表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500 && options / 3 > 0) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= options / 3;// 每次都减少30%
        }

        return baos.toByteArray();

    }

    /**
     * 方法概述：图片质量压缩
     */
    protected static Bitmap compressImageSize(Bitmap image) {
        if (image == null)
            return null;
        ByteArrayInputStream isBm = new ByteArrayInputStream(compressImageSizeToByteArray(image));// 把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

    }

    /**
     * 将Bitmap 对象转化为Base64字符串
     *
     * @param bitmap 图片
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将Base64字符串转化为图片
     */
    public static Bitmap base64ToBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
