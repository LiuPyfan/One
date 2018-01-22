package com.fantasy.pf.one.model.http;

import com.fantasy.pf.one.model.bean.OneIdBean;

import io.reactivex.Flowable;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public interface HttpHelper {
    Flowable<OneIdBean> fetchOneIdList();
}
