package com.kangwencai.appframework_mvp_retrofit.model.bean.zhihu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */

public class ZhihuStory implements Serializable {
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private ArrayList<String> images;
}
