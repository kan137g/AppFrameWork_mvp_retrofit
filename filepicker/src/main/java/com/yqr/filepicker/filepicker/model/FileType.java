package com.yqr.filepicker.filepicker.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description: 打开文件
 * Author: kangwencai
 * DATA: Date on 2018/8/10.
 * PS: Not easy to write code, please indicate.
 */

public class FileType implements Parcelable {
    private String title;
    private int iconStyle;
    public String[] filterType;

    public FileType() {
    }

    public FileType(String title, String[] filterType, int iconStyle) {
        this.title = title;
        this.iconStyle = iconStyle;
        this.filterType = filterType;
    }

    protected FileType(Parcel in) {
        title = in.readString();
        iconStyle = in.readInt();
        filterType = in.createStringArray();
    }

    public static final Creator<FileType> CREATOR = new Creator<FileType>() {
        @Override
        public FileType createFromParcel(Parcel in) {
            return new FileType(in);
        }

        @Override
        public FileType[] newArray(int size) {
            return new FileType[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getIconStyle() {
        return iconStyle;
    }

    public String[] getFilterType() {
        return filterType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIconStyle(int iconStyle) {
        this.iconStyle = iconStyle;
    }

    public void setFilterType(String[] filterType) {
        this.filterType = filterType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(iconStyle);
        dest.writeStringArray(filterType);
    }
}
