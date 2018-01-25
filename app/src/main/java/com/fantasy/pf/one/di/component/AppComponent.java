package com.fantasy.pf.one.di.component;

import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.di.module.AppModule;
import com.fantasy.pf.one.di.module.HttpModule;
import com.fantasy.pf.one.model.DataManagerModel;
import com.fantasy.pf.one.model.db.DBHelper;
import com.fantasy.pf.one.model.http.HttpHelper;
import com.fantasy.pf.one.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})// 指明在哪个包里找依赖
public interface AppComponent {
    // 从上面指定的依赖库AppModule.class中取得的对象 返回值为对象类
    OneApplication getContext();

    DataManagerModel getDataManagerModel();

    HttpHelper getHttpHelper();

    DBHelper getDbHelper();

    PreferencesHelper getPreferencesHelper();
}
