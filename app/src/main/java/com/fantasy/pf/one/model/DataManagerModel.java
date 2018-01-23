package com.fantasy.pf.one.model;

import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.model.db.DBHelper;
import com.fantasy.pf.one.model.http.HttpHelper;
import com.fantasy.pf.one.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public class DataManagerModel implements HttpHelper,DBHelper,PreferencesHelper {

    private HttpHelper mHttpHelper;
    private DBHelper mDBDbHelper;
    private PreferencesHelper mPreferencesHelper;

    public DataManagerModel(HttpHelper httpHelper, DBHelper DBDbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDBDbHelper = DBDbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Flowable<OneIdBean> fetchOneIdList() {
        return mHttpHelper.fetchOneIdList();
    }
}
