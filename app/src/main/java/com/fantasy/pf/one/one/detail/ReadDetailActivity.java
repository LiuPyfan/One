package com.fantasy.pf.one.one.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.base.MvpBaseActivity;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailContract;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailPresenter;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadDetailActivity extends MvpBaseActivity<ReadDetailPresenter> implements ReadDetailContract.View{

    @BindView(R.id.html_text)
    HtmlTextView mHtmlText;

    @Override
    public int getLayout() {
        return R.layout.activity_read_detail;
    }

    @Override
    public void setInject() {
        // 没注入 会 导入编译失败 找不到DaggerAppComponent
        getActivityComponent().inject(this);
    }


    @Override
    public void init() {
//        presenter.loadReadDetail(0);
        presenter.loadMovieDetail(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void showContent(String content) {
        mHtmlText.setHtml(content,new HtmlHttpImageGetter(mHtmlText));
    }
}