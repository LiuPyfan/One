package com.fantasy.pf.one.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.fantasy.pf.one.di.component.AppComponent;
import com.fantasy.pf.one.di.component.DaggerAppComponent;
import com.fantasy.pf.one.di.module.AppModule;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */

public class OneApplication extends Application{
    private static ImageLoader sImageLoader;
    private static OneApplication sInstance;
    private List<Activity> mActivityList;
    private static AppComponent sAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }
        mActivityList = new ArrayList<>();
    }

    public static OneApplication getInstance(){
        if (sInstance == null){
            return  new OneApplication();
        }else {
            return sInstance;
        }
    }

    public static ImageLoader getImageLoader(Context context){
        if (sImageLoader == null) {
            synchronized (ImageLoader.class){
                if (sImageLoader == null){
                    sImageLoader = ImageLoader.getInstance();
                    sImageLoader.init(ImageLoaderConfiguration.createDefault(context.getApplicationContext()));
                }
            }
        }
        return sImageLoader;
    }



    public static AppComponent getAppComponent(){
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(sInstance)).build();

        }
        return sAppComponent;
    }


    public void addActivity(Activity activity){
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    public void removeAllActivity(){
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
