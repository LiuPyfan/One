package com.fantasy.pf.one.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fantasy.pf.one.application.OneApplication;

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
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo.isConnected();
    }
}
