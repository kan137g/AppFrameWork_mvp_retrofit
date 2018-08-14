package com.yqr.filepicker.filepicker.util;

import android.telephony.mbms.FileInfo;

import com.yqr.filepicker.R;
import com.yqr.filepicker.filepicker.PickerManager;
import com.yqr.filepicker.filepicker.model.FileEntity;
import com.yqr.filepicker.filepicker.model.FileType;

import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FileUtils {
    /**
     * 根据路径获取file的集合
     *
     * @param path
     * @param filter
     * @return
     */
    public static List<FileEntity> getFileListByDirPath(String path, FileFilter filter) {
        File directory = new File(path);
        File[] files = directory.listFiles(filter);

        if (files == null) {
            return new ArrayList<>();
        }

        List<File> result = Arrays.asList(files);
        Collections.sort(result, new FileComparator());

        List<FileEntity> entities = new ArrayList<>();
        for (File f : result) {
            String absolutePath = f.getAbsolutePath();
            FileEntity e;
            if (checkExits(absolutePath)) {
                e = new FileEntity(absolutePath, f, true);
            } else {
                e = new FileEntity(absolutePath, f, false);
            }
            FileType fileType = getFileTypeNoFolder(PickerManager.getInstance().mFileTypes, absolutePath);
            e.setFileType(fileType);
            if (PickerManager.getInstance().files.contains(e)) {
                e.setSelected(true);
            }
            entities.add(e);
        }
        return entities;
    }

    private static boolean checkExits(String path) {
        for (FileEntity entity : PickerManager.getInstance().files) {
            if (entity.getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }

    public static String getReadableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#.##").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static FileType getFileType(ArrayList<FileType> fileTypes, String path) {
        for (String str : PickerManager.getInstance().mFilterFolder) {//按文件夹筛选
            if (path.contains(str)) {
                for (int index = 0; index < fileTypes.size(); index++) {//按照文件类型筛选
                    for (String string : fileTypes.get(index).filterType) {
                        if (path.endsWith(string))
                            return fileTypes.get(index);
                    }
                }
            }
        }
        return null;
    }

    //不包含文件夹
    public static FileType getFileTypeNoFolder(ArrayList<FileType> fileTypes, String path) {
        for (int index = 0; index < fileTypes.size(); index++) {//按照文件类型筛选
            for (String string : fileTypes.get(index).filterType) {
                if (path.endsWith(string))
                    return fileTypes.get(index);
            }
        }
        return null;
    }

    /**
     * @param file 文件
     * @return
     */
    public static FileEntity getFileInfoFromFile(File file) {


        FileEntity fileInfo = new FileEntity();
        fileInfo.setName(file.getName());
        fileInfo.setPath(file.getPath());
        fileInfo.setSize(getFileSzie(file.length()));
//        fileInfo.setDirectory(file.isDirectory());
        fileInfo.setDate(getFileLastModifiedTime(file));
        int lastDotIndex = file.getName().lastIndexOf(".");
        FileType fileType = initDocTypes(PickerManager.getInstance().getFileTypes(), file.getPath());
        fileInfo.setFileType(fileType);
        if (lastDotIndex > 0) {
            String fileSuffix = file.getName().substring(lastDotIndex + 1);
//            fileInfo.setSuffix(fileSuffix);
        }
        return fileInfo;
    }

    public static FileType initDocTypes(ArrayList<FileType> fileTypes, String path) {
        for (int index = 0; index < fileTypes.size(); index++) {//按照文件类型筛选
            for (String string : fileTypes.get(index).filterType) {
                if (path.endsWith(string))
                    return fileTypes.get(index);
            }
        }

        return null;
    }

    /**
     * 读取文件的最后修改时间的方法
     */
    public static String getFileLastModifiedTime(File f) {
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

    /****
     * 计算文件大小
     *
     * @param length
     * @return
     */
    public static String getFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

}
