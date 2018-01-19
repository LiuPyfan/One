package com.fantasy.pf.one.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.application.OneApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "pf";
    public ProgressDialog progressDialog;
    public Snackbar snackbar;

    public abstract View initView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState);

    public abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    // 显示进度
    public void showProgressDialog(int id) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.setMessage(getString(id));
        progressDialog.show();

    }
    // 隐藏进度
    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    public void showSnackBar(int strId) {
//        showSnackBar(getString(strId));
    }

    public void showSnackBar(String str) {
//        snackbar = Snackbar.make(getActivity().findViewById(R.id.layout_main_content), str,
//                Snackbar.LENGTH_SHORT);
//        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.radio_cover_color));
//        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
       // todo LeakCanary 查泄漏
    }

    @Subscribe
    public void onEventMainThread(String flags) {
        Log.d(TAG, flags);
    }

}
