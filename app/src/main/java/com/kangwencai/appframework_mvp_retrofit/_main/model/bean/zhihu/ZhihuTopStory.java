package com.kangwencai.appframework_mvp_retrofit._main.model.bean.zhihu;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public class ZhihuTopStory {
    /**
     * image : https://pic4.zhimg.com/v2-671ff79c5f9d00c7d4012328ce1d08d7.jpg
     * type : 0
     * id : 9687720
     * ga_prefix : 062616
     * title : 「这么过分的事，你居然还觉得无所谓？」
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

}
