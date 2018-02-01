package com.fantasy.pf.one.one.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.base.MvpBaseActivity;
import com.fantasy.pf.one.model.bean.ContentListBean;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailContract;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailPresenter;
import com.fantasy.pf.one.utils.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;

public class ReadDetailActivity extends MvpBaseActivity<ReadDetailPresenter> implements ReadDetailContract.View{

    @BindView(R.id.rich_text)
    XRichText mRichText;

    // 图文混排 图片
    Bitmap mBitmap;

    private ContentListBean mContentListBean;

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
//        presenter.loadMovieDetail(0);
        mContentListBean = (ContentListBean) getIntent().getSerializableExtra(Constants.ONE_LIST_BEAN);
        presenter.loadDetail(mContentListBean);
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
        mRichText.callback(new XRichText.BaseClickCallback(){
            @Override
            public void onImageClick(List<String> urlList, int position) {
                super.onImageClick(urlList, position);
            }

            @Override
            public boolean onLinkClick(String url) {
                return super.onLinkClick(url);
            }

            @Override
            public void onFix(XRichText.ImageHolder holder) {
                super.onFix(holder);
                // 设置宽高
                holder.setWidth(mRichText.getWidth());
                int height = mBitmap.getHeight() *(mRichText.getWidth() / mBitmap.getWidth());
                holder.setHeight(height < 800 ? 800 : height);
            }
        })
        .imageDownloader(new ImageLoader() {
            @Override
            public Bitmap getBitmap(String url) throws IOException {
                mBitmap = OneApplication.getImageLoader(ReadDetailActivity.this).loadImageSync(url);
                return mBitmap;
            }
        })
        .text(content);
    }
}