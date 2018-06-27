package com.kangwencai.business.model.bean.zhihu;

import java.util.ArrayList;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public class ZhiHuDaily {


    /**
     * date : 20180626
     * stories : [{"images":["https://pic2.zhimg.com/v2-bcb9a201e1a9ed4f7a5cb7c9bacb7bdd.jpg"],"type":0,"id":9687720,"ga_prefix":"062616","title":"「这么过分的事，你居然还觉得无所谓？」"},{"images":["https://pic1.zhimg.com/v2-37b45a060c5b2639ebf6b64c1150e3bc.jpg"],"type":0,"id":9687749,"ga_prefix":"062615","title":"四面迎敌、仍在亏损状态的美团，为什么偏偏选在此刻上市？"},{"images":["https://pic4.zhimg.com/v2-fc88df7bdba0ccc4bf7e2ad0ae953b13.jpg"],"type":0,"id":9687735,"ga_prefix":"062614","title":"28 年后再在内地影院看到《阿飞正传》，你心中有何感慨？"},{"images":["https://pic3.zhimg.com/v2-43c342727cb5db5a1c7c713514b2256a.jpg"],"type":0,"id":9687757,"ga_prefix":"062613","title":"一次决定性的 VAR 判罚，活活让昨晚的世界杯上演了戏剧性反转"},{"images":["https://pic2.zhimg.com/v2-fd69e8a873a78dfe675f026dc89c79c9.jpg"],"type":0,"id":9687459,"ga_prefix":"062612","title":"大误 · 净是些打破次元壁的故事"},{"images":["https://pic4.zhimg.com/v2-93a97b15be90a76987afdcb14862c6ab.jpg"],"type":0,"id":9687661,"ga_prefix":"062611","title":"给新员工办理入职，别一不留神给自己挖了个坑"},{"images":["https://pic1.zhimg.com/v2-b661bd7f907881a8a95a829368332ee4.jpg"],"type":0,"id":9687512,"ga_prefix":"062610","title":"秒变蜘蛛侠，我们科学家才没有在做梦呢"},{"images":["https://pic4.zhimg.com/v2-d188260d5f0c94ae7ec73d22d6c85ec3.jpg"],"type":0,"id":9687042,"ga_prefix":"062609","title":"趁大老板外出不好好做实验的结果就是\u2026\u2026诞生了医学史上一项重大发明"},{"images":["https://pic2.zhimg.com/v2-02390c1ea4745dea245227e2ff88a985.jpg"],"type":0,"id":9686147,"ga_prefix":"062608","title":"为什么农村的环境容易让人认知落后？"},{"images":["https://pic3.zhimg.com/v2-196fea48f45bc7a9c3d5c034d2b6894a.jpg"],"type":0,"id":9687705,"ga_prefix":"062607","title":"报个好志愿 · 文科生现在念什么专业好？"},{"images":["https://pic2.zhimg.com/v2-f7a6c1254c8a6e1348ebefa4838eaff1.jpg"],"type":0,"id":9686545,"ga_prefix":"062607","title":"终于，我下定决心离开那个日薄西山的行业"},{"images":["https://pic2.zhimg.com/v2-8dfd2b6b0ece4c4a145c3abd2f556bd1.jpg"],"type":0,"id":9687697,"ga_prefix":"062606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-671ff79c5f9d00c7d4012328ce1d08d7.jpg","type":0,"id":9687720,"ga_prefix":"062616","title":"「这么过分的事，你居然还觉得无所谓？」"},{"image":"https://pic2.zhimg.com/v2-f93b0cfe39b5213f22103f714f55945d.jpg","type":0,"id":9687757,"ga_prefix":"062613","title":"一次决定性的 VAR 判罚，活活让昨晚的世界杯上演了戏剧性反转"},{"image":"https://pic4.zhimg.com/v2-6b07a999180b86a7c4f41076b078840b.jpg","type":0,"id":9687749,"ga_prefix":"062615","title":"四面迎敌、仍在亏损状态的美团，为什么偏偏选在此刻上市？"},{"image":"https://pic1.zhimg.com/v2-967efb462bba422099040dfd10a7cf10.jpg","type":0,"id":9687661,"ga_prefix":"062611","title":"给新员工办理入职，别一不留神给自己挖了个坑"},{"image":"https://pic1.zhimg.com/v2-c1c965b18ba5684d91c8b5a10bf148d0.jpg","type":0,"id":9687512,"ga_prefix":"062610","title":"秒变蜘蛛侠，我们科学家才没有在做梦呢"}]
     */

    private String date;
    private ArrayList<ZhihuStory> stories;
    private ArrayList<ZhihuTopStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ZhihuStory> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuStory> stories) {
        this.stories = stories;
    }

    public ArrayList<ZhihuTopStory> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(ArrayList<ZhihuTopStory> top_stories) {
        this.top_stories = top_stories;
    }


}
