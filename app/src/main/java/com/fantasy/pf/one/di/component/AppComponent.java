package com.fantasy.pf.one.di.component;

import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    OneApplication getContext();
}
