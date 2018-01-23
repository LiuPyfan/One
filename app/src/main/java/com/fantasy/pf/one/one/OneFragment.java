package com.fantasy.pf.one.one;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.base.MvpBaseFragment;
import com.fantasy.pf.one.one.mvp.OnePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends MvpBaseFragment<OnePresenter> {
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void init() {
        presenter.getOneIdList();

    }



}
