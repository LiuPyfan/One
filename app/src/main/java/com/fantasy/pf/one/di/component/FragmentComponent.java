package com.fantasy.pf.one.di.component;

import android.app.Activity;

import com.fantasy.pf.one.di.module.FragmentModule;
import com.fantasy.pf.one.di.scope.FragmentScope;

import dagger.Component;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
