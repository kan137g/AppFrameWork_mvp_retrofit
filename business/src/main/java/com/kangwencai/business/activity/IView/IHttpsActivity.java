package com.kangwencai.business.activity.IView;


import com.kangwencai.business.model.bean.zhihu.ZhihuTopStory;

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
