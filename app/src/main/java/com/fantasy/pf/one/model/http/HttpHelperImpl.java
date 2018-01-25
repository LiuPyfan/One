package com.fantasy.pf.one.model.http;

import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.model.http.api.OneApis;
import com.fantasy.pf.one.model.http.response.MyHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public class HttpHelperImpl implements HttpHelper {

    private OneApis mOneApis;

    @Inject
    public HttpHelperImpl(OneApis oneApis) {
        mOneApis = oneApis;
    }

    @Override
    public Flowable<OneIdBean> fetchOneId() {
        return mOneApis.getOneId();
    }

    @Override
    public Flowable<MyHttpResponse<OneListBean>> getOneList(String id) {
        return mOneApis.getOneList(id);
    }

//    @Override
//    public Flowable<OneIdBean> fetchOneIdList() {
//        return mOneApis.getOneIdList();
//    }
}
