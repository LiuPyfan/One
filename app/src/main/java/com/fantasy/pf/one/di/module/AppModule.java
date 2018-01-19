package com.fantasy.pf.one.di.module;

import com.fantasy.pf.one.application.OneApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 *
 * module 提供app对象
 * Singleton 单例，防止引用两个
 */

@Module
public class AppModule {
    private OneApplication mOneApplication;

    public AppModule(OneApplication oneApplication) {
        mOneApplication = oneApplication;
    }

    @Provides
    @Singleton
    OneApplication provideOneApplicaiton(){
        return mOneApplication;
    }
}
