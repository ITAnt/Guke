package com.tongcent.guke.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by Jason on 2016/4/22.
 */
public class GukeUtils {

    private GukeUtils() {}

    private static class GukeFactory {
        private static GukeUtils instance = new GukeUtils();
    }

    public static GukeUtils getInstance() {
        return GukeFactory.instance;
    }

    public boolean hasLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String user = preferences.getString("user", null);
        return !TextUtils.isEmpty(user);
    }
}
