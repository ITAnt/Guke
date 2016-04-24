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


}
