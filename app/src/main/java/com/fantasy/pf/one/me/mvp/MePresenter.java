package com.fantasy.pf.one.me.mvp;

import com.fantasy.pf.one.base.RxPresenter;

import javax.inject.Inject;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public class MePresenter extends RxPresenter<MeContract.View> implements MeContract.Presenter{
    @Inject
    public MePresenter() {
    }
}
