package com.fantasy.pf.one.di.module;

import android.app.Activity;
import android.app.Fragment;

import com.fantasy.pf.one.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity(){
        return mFragment.getActivity();
    }
}
