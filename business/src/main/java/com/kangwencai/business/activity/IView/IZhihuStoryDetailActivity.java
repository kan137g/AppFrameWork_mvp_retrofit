package com.kangwencai.business.activity.IView;

import com.kangwencai.business.model.bean.zhihu.ZhihuStoryContent;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/27.
 * PS: Not easy to write code, please indicate.
 */
public interface IZhihuStoryDetailActivity {

    void loadZhihuStory();

    void loadFail(String errmsg);

    void loadSuccess(ZhihuStoryContent zhihuStory);
}
