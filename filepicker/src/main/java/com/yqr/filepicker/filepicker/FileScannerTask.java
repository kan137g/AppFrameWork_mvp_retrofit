package com.yqr.filepicker.filepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.telephony.mbms.FileInfo;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;


import com.yqr.filepicker.filepicker.activity.FilePickerActivity;
import com.yqr.filepicker.filepicker.model.FileEntity;
import com.yqr.filepicker.filepicker.model.FileType;
import com.yqr.filepicker.filepicker.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.MediaColumns.DATA;


/**
 * Description:   扫描媒体库获取数据
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FileScannerTask extends AsyncTask<Void, Void, List<FileEntity>> {


    private int selectType;
    private Context context;
    private FileScannerListener mFileScannerListener;

    public interface FileScannerListener {
        void scannerResult(List<FileEntity> entities);
    }

    private final String[] DOC_PROJECTION = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Files.FileColumns.TITLE
    };


    public FileScannerTask(Context context, FileScannerListener fileScannerListener) {
        this.context = context;
        mFileScannerListener = fileScannerListener;
    }

    public FileScannerTask(Context context, int fileType, FileScannerListener fileScannerListener) {
        this.context = context;
        this.selectType = fileType;
        mFileScannerListener = fileScannerListener;
    }

    //MediaStore.Files存储了所有应用中共享的文件，包括图片、文件、视频、音乐等多媒体文件，包括非多媒体文件。
    @Override
    protected List<FileEntity> doInBackground(Void... params) {
        List<FileEntity> fileEntities;
        fileEntities = getDocumentData(selectType);
//        fileEntities = getDocumentData(2);
//        fileEntities = getDocumentData2(2);
        return fileEntities;
    }


    /**
     * 获取手机文档数据
     *
     * @param selectType 选择的类型
     */
    private List<FileEntity> getDocumentData(int selectType) {
        List<FileEntity> fileEntities = new ArrayList<>();
        String select = "";
        switch (selectType) {
            case FilePickerActivity.WORD:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" + " or "
                        + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" + ")";
                break;
            case FilePickerActivity.EXCEL:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.xls'" + " or "
                        + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsx'" + ")";
                break;
            case FilePickerActivity.PPT:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppt'" + " or "
                        + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptx'" + ")";
                break;
            case FilePickerActivity.PDF:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.pdf'" + ")";
                break;
        }

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(
                MediaStore.Files.getContentUri("external"),
                DOC_PROJECTION, select, null, null);
//        Cursor cursor = contentResolver.query(Uri.parse(Environment.getExternalStorageDirectory() + "/tencent/QQfile_recv/"), columns, select, null, null);


        if (cursor != null) {
            fileEntities = getFiles(cursor);
            cursor.close();
        }
        return fileEntities;
    }

    @Override
    protected void onPostExecute(List<FileEntity> entities) {
        super.onPostExecute(entities);
        if (mFileScannerListener != null) {
            mFileScannerListener.scannerResult(entities);
        }
    }

    /**
     * 将搜索到的数据转为指定类型的数据
     *
     * @param cursor SQL游标
     * @return
     */
    private List<FileEntity> getFiles(Cursor cursor) {
        List<FileEntity> fileEntities = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String path = cursor.getString(cursor.getColumnIndexOrThrow(DATA));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE));
            if (path != null) {
                FileType fileType = FileUtils.initDocTypes(PickerManager.getInstance().getFileTypes(), path);
                if (fileType != null && !(new File(path).isDirectory())) {

                    FileEntity entity = new FileEntity(id, title, path);
                    entity.setFileType(fileType);

                    String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE));
                    if (mimeType != null && !TextUtils.isEmpty(mimeType))
                        entity.setMimeType(mimeType);
                    else {
                        entity.setMimeType("");
                    }

                    entity.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)));
//                    String size = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE));
//                    entity.setSize(FileUtils.getFileSzie(Long.parseLong(size)));
                    if (PickerManager.getInstance().files.contains(entity)) {
                        entity.setSelected(true);
                    }
                    if (!fileEntities.contains(entity))
                        fileEntities.add(entity);
                }


            }
        }
        return fileEntities;
    }

    /**
     * 查找文件的方式 ,这种方式怎么找都找不到excel，先不管了
     *
     * @param selectType 指定的文件格式
     * @return
     */
    @Deprecated
    private List<FileEntity> getDocumentData2(int selectType) {
        List<FileEntity> fileEntities = new ArrayList<>();
//        String selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
//                + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";
//        String[] selectionArgs = new String[]{
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("text"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("doc"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("docx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("dotx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("dotx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("ppt"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("pptx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("potx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("ppsx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("xls"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("xlsx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("xltx"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpg"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpg"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("png"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("svg"),
//                MimeTypeMap.getSingleton().getMimeTypeFromExtension("gif")
//        };
//
//        for (String str : selectionArgs) {
//            Log.i("selectionArgs", str);
//        }


        String selection;
        String[] selectionArgs;
        if (selectType == FilePickerActivity.PDF) {
            selection = MediaStore.Files.FileColumns.MIME_TYPE + "=?";

            selectionArgs = new String[]{
                    MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")};
        } else {
            selection = "(" + MediaStore.Files.FileColumns.DATA + " LIKE ?" +
                    " or " + MediaStore.Files.FileColumns.DATA + " LIKE ?" + ")";
//            selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ? " ;
            selectionArgs = new String[]{"%.xls", "%.xlsx"};
            for (String str : selectionArgs) {
                Log.i("selectionArgs", str);
            }
        }


        try {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Files.getContentUri("external"),//数据源
                    DOC_PROJECTION,//查询类型
                    selection,//查询条件
                    selectionArgs,
                    MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC");
            if (cursor != null) {
                fileEntities = getFiles(cursor);
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileEntities;
    }
}
