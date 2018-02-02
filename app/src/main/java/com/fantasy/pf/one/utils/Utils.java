package com.fantasy.pf.one.utils;

import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.application.OneApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 * 手机是否联网
 */

public class Utils {
    public static boolean isNetworkConnected() {
        //6.0 之后得使用 getApplicationContext()..getSystemService(...)
        //否则会内存泄漏
        ConnectivityManager connectivityManager = (ConnectivityManager) OneApplication.getInstance().
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
        }
        return false;

    }

    // 配置uil
    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

    }

    // 配置uil
    public static DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_launcher_round, 0);
    }

    public static void displayImage(Context context, String uri, ImageView imageView) {
        OneApplication.getImageLoader(context).displayImage(uri, imageView, getImageOptions());
    }

    public static void displayImage(Context context, String uri, ImageView imageView, DisplayImageOptions displayImageOptions) {
        OneApplication.getImageLoader(context).displayImage(uri, imageView, displayImageOptions);
    }

    public static String showDate(Context context, String date) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        String year = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String month = formatData(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String day = formatData(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (date.contains(year + "-" + month + "-" + day)) {
            return context.getString(R.string.today);
        } else {
            date = date.substring(0, 10);
            String[] dates = date.split("-");
            return dates[1] + context.getString(R.string.month) + dates[2] + context.getString(R.string.
                    day);
        }
    }

    public static String formatData(int data) {
        return data < 10 ? "0" + data : data + "";
    }

    public static int getCurrentViewIndex(LinearLayoutManager linearLayoutManager){
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        int currentIndex = firstVisibleItem;
        int lastHeight = 0;
        for (int i = firstVisibleItem; i <= lastVisibleItem; i++){
            View view = linearLayoutManager.getChildAt(i-firstVisibleItem);
            if (null == view){
                continue;
            }

            int[] location = new int[2];
            view.getLocationOnScreen(location);

            Rect localRect = new Rect();
            view.getLocalVisibleRect(localRect);

            int showHeight = localRect.bottom - localRect.top;
            if (showHeight > lastHeight){
                currentIndex = i;
                lastHeight = showHeight;
            }
        }

        if (currentIndex < 0){
            currentIndex = 0;
        }

        return currentIndex;
    }

    public static String formatDate(String date) {
        return date.split(" ")[0].replace("-",
                "<font color='#878787'> / </font>");
    }
}
