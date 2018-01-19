package com.fantasy.pf.one.di.component;

import android.app.Activity;

import com.fantasy.pf.one.di.module.ActivityModule;
import com.fantasy.pf.one.di.module.AppModule;
import com.fantasy.pf.one.di.scope.ActivityScope;

import dagger.Component;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */
// @ActivityScope 局部作用域有单例效果 让MainAty和AAty共享一个 BAty单独一个
//dependencies 依赖关系 modules作用module
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


}
