package com.fantasy.pf.one.one.mvp;

import com.fantasy.pf.one.base.BasePresenter;
import com.fantasy.pf.one.base.BaseView;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public class OneContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{

    }
}
