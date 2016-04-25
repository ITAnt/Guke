package com.tongcent.guke.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/4/25.
 */
public class SumClicks extends BmobObject {

    private long clicks;

    public long getClicks() {
        return clicks;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }
}
