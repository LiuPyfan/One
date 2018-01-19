package com.fantasy.pf.one.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fantasy.pf.one.application.OneApplication;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "pf";
    private OneApplication mApplication;

    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = OneApplication.getInstance();
        addActivity();
        onCreateView();
        init();

    }

    public void onCreateView() {
    }

    public void addActivity() {
        mApplication.addActivity(this);
    }
}
