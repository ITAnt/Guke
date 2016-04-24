package com.tongcent.guke;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Jason on 2016/4/24.
 */
public class GukeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
