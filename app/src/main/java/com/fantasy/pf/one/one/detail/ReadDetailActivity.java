package com.fantasy.pf.one.one.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.application.OneApplication;
import com.fantasy.pf.one.base.MvpBaseActivity;
import com.fantasy.pf.one.model.bean.AuthorBean;
import com.fantasy.pf.one.model.bean.CommentBean;
import com.fantasy.pf.one.model.bean.ContentListBean;
import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.MusicDetailBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailContract;
import com.fantasy.pf.one.one.detail.mvp.ReadDetailPresenter;
import com.fantasy.pf.one.utils.Constants;
import com.fantasy.pf.one.utils.HtmlUtil;
import com.fantasy.pf.one.utils.Utils;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;

public class ReadDetailActivity extends MvpBaseActivity<ReadDetailPresenter> implements ReadDetailContract.View {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;


    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.iv_author)
    ImageView ivAuthor;
    @BindView(R.id.tv_hp_author)
    TextView tvHpAuthor;
    @BindView(R.id.tv_auth_it)
    TextView tvAuthIt;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // 图文混排 图片
    Bitmap mBitmap;

    private ContentListBean mContentListBean;

    private CommentAdapter mCommentAdapter;

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
        initToolbar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mContentListBean = (ContentListBean) getIntent().getSerializableExtra(Constants.ONE_LIST_BEAN);
        tvTitle.setText(mContentListBean.getShareList().getWx().getTitle().split("\\|")[0].
                trim());
        tvDetailTitle.setText(mContentListBean.getTitle());
        tvUserName.setText(mContentListBean.getShareList().getWx().getDesc().split(" ")[0].trim());
        initWebView();
        presenter.loadDetail(mContentListBean);
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void showErrorMsg(String msg) {

    }

//    @Override
//    public void showContent(String content) {
//        mRichText.callback(new XRichText.BaseClickCallback(){
////            @Override
////            public void onImageClick(List<String> urlList, int position) {
////                super.onImageClick(urlList, position);
////            }
////
////            @Override
////            public boolean onLinkClick(String url) {
////                return super.onLinkClick(url);
////            }
//
//            @Override
//            public void onFix(XRichText.ImageHolder holder) {
//                super.onFix(holder);
//                // 设置宽高
//                holder.setWidth(mRichText.getWidth());
//                int height = mBitmap.getHeight() *(mRichText.getWidth() / mBitmap.getWidth());
//                holder.setHeight(height < 800 ? 800 : height);
//            }
//        })
//        .imageDownloader(new ImageLoader() {
//            @Override
//            public Bitmap getBitmap(String url) throws IOException {
//                mBitmap = OneApplication.getImageLoader(ReadDetailActivity.this).loadImageSync(url);
//                return mBitmap;
//            }
//        })
//        .text(content.split("</head>")[1]); // 去除头
//    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showReadData(ReadDetailBean readDetailBean) {
//        mRichText.callback(new XRichText.BaseClickCallback() {
//            @Override
//            public void onFix(XRichText.ImageHolder holder) {
//                super.onFix(holder);
//                // 设置宽高
//                holder.setWidth(mRichText.getWidth());
//                int height = mBitmap.getHeight() * (mRichText.getWidth() / mBitmap.getWidth());
//                holder.setHeight(height < 800 ? 800 : height);
//            }
//        })
//                .imageDownloader(new ImageLoader() {
//                    @Override
//                    public Bitmap getBitmap(String url) throws IOException {
//                        mBitmap = OneApplication.getImageLoader(ReadDetailActivity.this).loadImageSync(url);
//                        return mBitmap;
//                    }
//                })
//                .text(readDetailBean.getHpContent().split("</head>")[1].replace(
//                "\\s", "")); // 去除头

        List<String> list = new ArrayList<>();
        list.add("http://resource.wufazhuce.com/one.css?v=4.3.1");
        List<String> list1 = new ArrayList<>();
//        list1.add("http://resource.wufazhuce.com/one-zepto.min.js");
//        list1.add("http://resource.wufazhuce.com/one-vue.min.js");
//        list1.add("http://resource.wufazhuce.com/one-webview.js?v=4.3.1");

        String htmlData = HtmlUtil.createHtmlData(readDetailBean.getHpContent(),list,list1);
        mWebView.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);


        tvIntroduce.setText(readDetailBean.getHpAuthorIntroduce() + " " + readDetailBean.getEditorEmail());

        AuthorBean authorBean = readDetailBean.getAuthor().get(0);
        Utils.displayImage(this, authorBean.getWebUrl(),
                ivAuthor, Utils.getImageOptions(R.mipmap.ic_launcher_round, 360));

        tvHpAuthor.setText(authorBean.getUserName() + " " + authorBean.getWbName());
        tvAuthIt.setText(authorBean.getDesc());
    }

    @Override
    public void showMovieData(MovieDetailBean readDetailBean) {

    }

    @Override
    public void showMusicData(MusicDetailBean readDetailBean) {

    }

    @Override
    public void showReadComment(CommentBean commentBean) {
        mCommentAdapter = new CommentAdapter(this,commentBean);
        recyclerView.setAdapter(mCommentAdapter);

    }
}