package com.fantasy.pf.one.main.mvp;

import com.fantasy.pf.one.base.BasePresenter;
import com.fantasy.pf.one.base.BaseView;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public interface MainContract {
    interface View extends BaseView{
        void switchOneView();
        void switchAllView();
        void switchMeView();

    }
    interface Presenter extends BasePresenter<View>{
       void switchNavView(int id);
    }
}
