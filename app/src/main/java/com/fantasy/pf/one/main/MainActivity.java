package com.fantasy.pf.one.main;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.all.AllFragment;
import com.fantasy.pf.one.base.MvpBaseActivity;
import com.fantasy.pf.one.main.mvp.MainContract;
import com.fantasy.pf.one.main.mvp.MainPresenter;
import com.fantasy.pf.one.me.MeFragment;
import com.fantasy.pf.one.model.bean.ContentListBean;
import com.fantasy.pf.one.one.OneFragment;
import com.fantasy.pf.one.utils.Utils;
import com.fantasy.pf.one.widget.HpTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.rb_one)
    RadioButton rbOne;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rb_me)
    RadioButton rbMe;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_hp_title)
    HpTextView tvHpTitle;
    private Fragment mCurrentFragment;
    private OneFragment mOneFragment;
    private AllFragment mAllFragment;
    private MeFragment mMeFragment;

    private boolean mRadioChanged;

    @Override
    public void setInject() {
        // 上一步再MvpBaseActivity
        getActivityComponent().inject(this);
    }

    @Override
    public void init() {
        isBack = false;
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
        mRadioChanged = true;
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

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.rb_one,R.id.rb_all,R.id.rb_me})
    public void onViewClicked(View view){
        if (!mRadioChanged){
            switch (view.getId()){
                case R.id.rb_one:
                    mOneFragment.scrollToTop();
                    break;
                case R.id.rb_all:
                    break;
                case R.id.rb_me:
                    break;
            }
        }
        mRadioChanged = false;
    }
    // 显示隐藏动画，在具体的frg中回调
    public void changeRadioGState(boolean isShow){
        Animation animation = AnimationUtils.loadAnimation(this,isShow?R.anim.rb_show:R.anim.rb_hide);
        animation.setFillAfter(true);
        layoutBottom.startAnimation(animation);
    }


    public void setToolBarTitle(String title){
//        tvTitle.setText(Html.fromHtml(title));
        tvHpTitle.setVisibility(View.VISIBLE);
        tvHpTitle.setText(Html.fromHtml(title));
    }

    public void setToolBarWeather(String weather){
        setToolBarWeatherState(true);
        tvWeather.setText(weather);
    }

    public void setToolBarWeatherState(boolean state) {
        tvWeather.setVisibility(state ? View.VISIBLE :View.GONE);
    }


    @SuppressLint("SetTextI18n")
    public void showPopup(ContentListBean contentListBean){
        View popView = LayoutInflater.from(this)
                .inflate(R.layout.pop_reporter,null);
        final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        TextView tvVolume = popView.findViewById(R.id.tv_volume);
        TextView tvTitle = popView.findViewById(R.id.tv_title);
        final ImageView ivCover = popView.findViewById(R.id.iv_cover);
        tvVolume.setText(contentListBean.getVolume());
        Utils.displayImage(this,contentListBean.getImgUrl(),ivCover);
        tvTitle.setText(contentListBean.getTitle() + " | " + contentListBean.getPicInfo());

        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setAnimationStyle(R.style.pop_animation);
        popupWindow.showAsDropDown(tvWeather);
    }
}
