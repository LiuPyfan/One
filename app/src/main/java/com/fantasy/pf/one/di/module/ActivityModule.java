package com.fantasy.pf.one.di.module;

import android.app.Activity;

import com.fantasy.pf.one.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 *
 */
// 使用@Module增加一个ActivityModule类，用于管理所有依赖
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
    // 使用@Provides，为每一个依赖写一个provideXxx方法
    @Provides
    @ActivityScope
    Activity provideActivity(){
        return mActivity;
    }
}
