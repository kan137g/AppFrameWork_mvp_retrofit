package com.kangwencai.common.base;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/26.
 * PS: Not easy to write code, please indicate.
 */
public interface IBaseActivity {
    /**
     * 填充数据
     */
    void fillView();

    /**
     * 从布局文件初始化界面
     */
    void initViewFromXML();

    /**
     * 初始化数据
     */
    void initData();


}
