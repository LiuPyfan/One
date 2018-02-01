package com.fantasy.pf.one.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.fantasy.pf.one.di.component.AppComponent;
import com.fantasy.pf.one.di.component.DaggerAppComponent;
import com.fantasy.pf.one.di.module.AppModule;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

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

//    private static ImageLoaderConfiguration.Builder mBuilder; // 构造者设置

//    private static ImageLoaderConfiguration mImageLoaderConfiguration;
//    private DisplayImageOptions mOptions; //展示设置
//    private DisplayImageOptions.Builder mOptionsBuilder; //展示设置builder
//    private static int maxPoolSize;

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
    /**弃用**/
//    public void setUIL(){
//        setUILBuilder(mBuilder);
//        setUILConfiguration(mOptions);
//    }
//    public DisplayImageOptions setUILConfiguration(DisplayImageOptions options) {
//        mOptions = options;
//        // 3.使用Bitmap.Config.RGB555代替Bitmap.Config.ARGB8888，减少内存占用。在DisplayImageOptions的.bitmapConfig中配置
//        Bitmap.Config config = Bitmap.Config.RGB_565;
//        mOptionsBuilder = new DisplayImageOptions.Builder();
//        mOptionsBuilder.bitmapConfig(config);
//        // 4. 在DisplayImageOption中配置imageScaleType(ImageScaleType.IN_SAMPLE_INT)或是imageScaleType(ImageScaleType.EXACTY)
//        mOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
//        mOptions = mOptionsBuilder.build();
//        return mOptions;
//    }
//
//    public static ImageLoaderConfiguration.Builder setUILBuilder(ImageLoaderConfiguration.Builder builder) {
//        mBuilder = builder;
//        // 1.减少线程池中线程的个数, 线程池1-5;
//        maxPoolSize = 3;
//        builder.threadPoolSize(maxPoolSize)
//                .memoryCache(new WeakMemoryCache());// 2. 在ImageLoaderConfiguration中配置图片缓存策略为.memoryCache(new WeakMemoryCache())
//        return builder;
//    }
    /**弃用**/
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
