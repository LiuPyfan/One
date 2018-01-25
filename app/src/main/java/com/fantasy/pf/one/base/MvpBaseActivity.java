package com.fantasy.pf.one.base;

import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.di.component.ActivityComponent;
import com.fantasy.pf.one.di.component.DaggerActivityComponent;
import com.fantasy.pf.one.di.component.DaggerAppComponent;
import com.fantasy.pf.one.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 * 经过ActivityModule->ActivityComponent 编译后 在getActivityComponent()生成
 */

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    public T presenter;

    public abstract void setInject();
    // module初始化
    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    public ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder() // build后的自动生成的类
                .appComponent(OneApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
        // 下一步再子类中 inject(this);
    }


    @Override
    public void onCreateView() {
        super.onCreateView();
        setInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!= null){
            presenter.detachView();
        }
    }
}
