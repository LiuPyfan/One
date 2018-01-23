package com.fantasy.pf.one.model.http;

import com.fantasy.pf.one.base.BaseView;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/23.
 * let none that wait on thee be ashamed
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private BaseView mBaseView;
    private String mErrotMsg;
    private boolean mIsShowErrorState = true;

    public CommonSubscriber(BaseView baseView) {
        mBaseView = baseView;
    }

    public CommonSubscriber(BaseView baseView, String errotMsg) {
        mBaseView = baseView;
        mErrotMsg = errotMsg;
    }

    public CommonSubscriber(BaseView baseView, boolean isShowErrorState) {
        mBaseView = baseView;
        mIsShowErrorState = isShowErrorState;
    }

    public CommonSubscriber(BaseView baseView, String errotMsg, boolean isShowErrorState) {
        mBaseView = baseView;
        mErrotMsg = errotMsg;
        mIsShowErrorState = isShowErrorState;
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
