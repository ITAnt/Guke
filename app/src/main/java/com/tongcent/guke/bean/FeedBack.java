package com.tongcent.guke.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/4/25.
 */
public class FeedBack extends BmobObject {

    private String phoneNumber;
    private String userName;
    private String content;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
