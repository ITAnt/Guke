package com.tongcent.guke.utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*public class TimeUtils {
    public static String getLocalDate() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }
}*/

/**
 * Created by Jason on 2016/4/24.
 */
public class TimeUtils {
    public static String getNetworkDate() {
        Calendar calendar = Calendar.getInstance();

        try {
            URL url = new URL("http://www.baidu.com");//取得资源对象
            URLConnection connection = url.openConnection();//生成连接对象
            connection.connect(); //发出连接
            Date date = new Date(connection.getDate()); //转换为标准时间对象
            calendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

    public static String getLocalDate() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

}