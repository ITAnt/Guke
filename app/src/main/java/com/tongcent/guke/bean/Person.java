package com.tongcent.guke.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Jason on 2016/4/24.
 */
public class Person extends BmobUser {

    private String phoneNumber;
    private String thePassword;
    private long clickNum;

    public long getClickNum() {
        return clickNum;
    }

    public void setClickNum(long clickNum) {
        this.clickNum = clickNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getThePassword() {
        return thePassword;
    }

    public void setThePassword(String thePassword) {
        this.thePassword = thePassword;
    }
}
