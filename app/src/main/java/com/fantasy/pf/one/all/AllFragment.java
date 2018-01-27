package com.fantasy.pf.one.all;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        return inflater.inflate(R.layout.fragment_all, null);

    }

    @Override
    public void init() {

    }



    @Override
    protected void initInject() {

    }


    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
