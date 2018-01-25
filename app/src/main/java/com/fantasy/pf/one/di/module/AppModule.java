package com.fantasy.pf.one.di.module;

import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.model.DataManagerModel;
import com.fantasy.pf.one.model.db.DBHelper;
import com.fantasy.pf.one.model.db.DBHelperImpl;
import com.fantasy.pf.one.model.http.HttpHelper;
import com.fantasy.pf.one.model.http.HttpHelperImpl;
import com.fantasy.pf.one.model.prefs.PreferencesHelper;
import com.fantasy.pf.one.model.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/18.
 * let none that wait on thee be ashamed
 *
 * 管理所有的依赖 为之后获取对象准备
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

    /**
     * dagger获取AppComponent里
     实例化sp,db,http的helper类
     */
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelper) {
        return httpHelper;
    }
    // db
    @Provides
    @Singleton
    DBHelper provideDBHelper(DBHelperImpl dbHelper) {
        return dbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    DataManagerModel provideDataManagerModel(HttpHelperImpl httpHelper, DBHelperImpl dbHelper,
                                             PreferencesHelperImpl preferencesHelper) {
        return new DataManagerModel(httpHelper, dbHelper, preferencesHelper);
    }

}
