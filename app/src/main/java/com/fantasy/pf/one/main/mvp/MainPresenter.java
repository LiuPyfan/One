package com.fantasy.pf.one.main.mvp;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.base.RxPresenter;

import javax.inject.Inject;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter(){

    }


    @Override
    public void switchNavView(int id) {
        switch (id) {
            case R.id.rb_one:
                view.switchOneView();
                break;
            case R.id.rb_all:
                view.switchAllView();
                break;
            case R.id.rb_me:
                view.switchMeView();
                break;
        }
    }

}
