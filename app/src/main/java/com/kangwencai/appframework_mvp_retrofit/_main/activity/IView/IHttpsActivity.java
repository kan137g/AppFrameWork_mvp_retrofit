package com.kangwencai.appframework_mvp_retrofit._main.activity.IView;


import com.kangwencai.appframework_mvp_retrofit._main.model.bean.zhihu.ZhihuTopStory;

import java.util.ArrayList;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */

public interface IHttpsActivity {


    void showResult(ArrayList<ZhihuTopStory> response);
}
