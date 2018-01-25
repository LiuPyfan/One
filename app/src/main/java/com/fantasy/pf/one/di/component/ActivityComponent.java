package com.fantasy.pf.one.di.component;

import android.app.Activity;

import com.fantasy.pf.one.di.module.ActivityModule;
import com.fantasy.pf.one.di.module.AppModule;
import com.fantasy.pf.one.di.scope.ActivityScope;
import com.fantasy.pf.one.main.MainActivity;

import dagger.Component;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 * 上一步是ActivityModule里provide一个对象
   在这各类是获取module里生成的类，在这个类中进行管理
    下一步再MvpBaseActivity基类中生成
 */

// @ActivityScope 局部作用域有单例效果 用 让MainAty和AAty共享一个 BAty单独一个
//dependencies 依赖关系 modules作用module
@ActivityScope // 局部修饰 inject(XXXActivity xxxActivity)
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    // 将以上的三个依赖注入到某个类中
    void inject(MainActivity mainActivity);
}
