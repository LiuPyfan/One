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
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return mActivity;
    }
}
