package com.fantasy.pf.one.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.all.AllFragment;
import com.fantasy.pf.one.base.MvpBaseActivity;
import com.fantasy.pf.one.main.mvp.MainContract;
import com.fantasy.pf.one.main.mvp.MainPresenter;
import com.fantasy.pf.one.me.MeFragment;
import com.fantasy.pf.one.one.OneFragment;

import butterknife.BindView;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private Fragment mCurrentFragment;
    private OneFragment mOneFragment;
    private AllFragment mAllFragment;
    private MeFragment mMeFragment;



    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void init() {
        initToolbar();
        mCurrentFragment = new OneFragment();
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.rb_one);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        presenter.switchNavView(checkedId);
    }

    @Override
    public void switchOneView() {
        if (mOneFragment == null) {
            mOneFragment = new OneFragment();
        }
        switchContent(mCurrentFragment,mOneFragment);
    }

    @Override
    public void switchAllView() {
        if (mAllFragment == null) {
            mAllFragment = new AllFragment();
        }
        switchContent(mCurrentFragment,mAllFragment);
    }

    @Override
    public void switchMeView() {
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
        }
        switchContent(mCurrentFragment,mMeFragment);
    }
    // 切换
    public void switchContent(Fragment from, Fragment to) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.hide(from).add(R.id.frame_content, to).commit();
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.hide(from).show(to).commit();
            }
        }
    }

}
