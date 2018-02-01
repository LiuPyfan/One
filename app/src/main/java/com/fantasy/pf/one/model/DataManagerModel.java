package com.fantasy.pf.one.model;

import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.MusicDetailBean;
import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;
import com.fantasy.pf.one.model.db.DBHelper;
import com.fantasy.pf.one.model.http.HttpHelper;
import com.fantasy.pf.one.model.http.response.MyHttpResponse;
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
    public Flowable<OneIdBean> fetchOneId() {
        return mHttpHelper.fetchOneId();
    }

    @Override
    public Flowable<MyHttpResponse<OneListBean>> getOneList(String id) {
        return mHttpHelper.getOneList(id);
    }


    @Override
    public Flowable<MyHttpResponse<ReadDetailBean>> getReadDetail(String itemId) {
        return mHttpHelper.getReadDetail(itemId);
    }

    @Override
    public Flowable<MyHttpResponse<MovieDetailBean>> getMovieDetail(String itemId) {
        return mHttpHelper.getMovieDetail(itemId);
    }

    // 获取HttpHelper
    @Override
    public Flowable<MyHttpResponse<MusicDetailBean>> getMusicDetail(String itemId) {
        return mHttpHelper.getMusicDetail(itemId);
    }

//    @Override
//    public Flowable<OneIdBean> fetchOneIdList() {
//        return mHttpHelper.fetchOneIdList();
//    }


}
