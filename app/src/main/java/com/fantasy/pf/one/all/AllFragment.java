package com.fantasy.pf.one.all;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.all.mvp.AllPresenter;
import com.fantasy.pf.one.base.MvpBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends MvpBaseFragment<AllPresenter> {


    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);

    }

    @Override
    public void init() {

    }



    @Override
    protected void initInject() {

    }
}
