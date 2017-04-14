package me.donnie.github.common.utils;

import android.net.Uri;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okio.ByteString;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */

public class Util {

    private static final String TAG = Util.class.getSimpleName();

    public static String generate(String name,
                                  String pwd) {
        try {
            String usernameAndPassword = name + ":" + pwd;
            byte[] bytes = usernameAndPassword.getBytes("ISO-8859-1");
            String encoded = ByteString.of(bytes).base64();
            return "Basic " + encoded;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

    public static List<String> parserUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            List<String> segments = uri.getPathSegments();
            if (segments == null || segments.size() < 2) {
                return null;
            }
            boolean isFirstPathIsRepo = (segments.get(0).equalsIgnoreCase("repos") || segments.get(0).equalsIgnoreCase("repo"));
            return isFirstPathIsRepo ? segments : null;
        } else {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String friendly_time(Date time) {
        if (time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater.get().format(cal.getTime());
        String paramDate = dateFormater.get().format(time);
        if (curDate.equals(paramDate)) {
            int minute = (int)((cal.getTimeInMillis() - time.getTime())/60000);
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (minute == 0){
                ftime = "刚刚";
            }else {
                if (hour == 0)
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                else
                    ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if (days > 2 && days < 31) {
            ftime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "3个月前";
        } else {
            ftime = dateFormater.get().format(time);
        }
        return ftime;
    }

}
