package com.fantasy.pf.one.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.widget.HpTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "pf";
    private OneApplication mApplication;

    public HpTextView tvTitle;
    // 物理键
    public boolean isBack = true;
    private long mExitTime;


    public abstract void init();

    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        ButterKnife.bind(this);

        mApplication = OneApplication.getInstance();
        addActivity();
        onCreateView();
        init();

    }


    public void onCreateView() {
    }

    public void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        // 工具栏设置文字
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            tvTitle = findViewById(R.id.tv_title);
            setTitle("");
            toolbar.setOnMenuItemClickListener(onMenuItemClick);
        }
        if (isBack) {
            toolbar.setNavigationIcon(R.mipmap.return_image_gray);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    // 事件
    @Subscribe
    public void onEventMainThread(String flags) {
        Log.d(TAG, flags);
    }


    // 工具栏点击事件接口
    public void OnMenuItemClick(int itemId) {

    }

    // toolbar点击事件
    public Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            OnMenuItemClick(menuItem.getItemId());
            return true;
        }
    };

    public boolean onExitActivity(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, R.string.exit_program_hint,
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                removeAllActivity();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void addActivity() {
        mApplication.addActivity(this);
    }

    public void removeAllActivity() {
        mApplication.removeAllActivity();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }



}
