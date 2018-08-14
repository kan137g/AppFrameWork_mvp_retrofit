package com.yqr.filepicker.filepicker;

import java.io.File;
import java.io.FileFilter;


/**
 * Description: File的筛选
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */
public class FileSelectFilter implements FileFilter {
    private String[] mTypes;

    public FileSelectFilter(String[] types) {
        this.mTypes = types;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        if (mTypes != null && mTypes.length > 0) {
            for (int i = 0; i < mTypes.length; i++) {
                if (file.getName().endsWith(mTypes[i].toLowerCase()) || file.getName().endsWith(mTypes[i].toUpperCase())) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
