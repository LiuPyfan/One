package com.fantasy.pf.one.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.di.component.DaggerFragmentComponent;
import com.fantasy.pf.one.di.component.FragmentComponent;
import com.fantasy.pf.one.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 */

public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    public T presenter;

    protected abstract void initInject();

    private FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    public FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(OneApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter!= null) {
            presenter.detachView();
        }
    }
}
