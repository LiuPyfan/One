package com.fantasy.pf.one.one.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    @BindView(R.id.iv_loading)
    ImageView ivLoading;
    @BindView(R.id.tv_hp_author)
    TextView tvHpAuthor;
    @BindView(R.id.tv_auth_it)
    TextView tvAuthIt;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.layout_bottom2)
    RelativeLayout layoutBottom2;
    @BindView(R.id.tv_like_num)
    TextView tvLikeNum;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;

    // 图文混排 图片
    Bitmap mBitmap;
    private AnimationDrawable mAnimationDrawable;

    private ContentListBean mContentListBean;

    private CommentAdapter mCommentAdapter;

    private boolean mIsBottomShow = true;

    @Override
    public int getLayout() {
        return R.layout.activity_read_detail;
    }

    @Override
    public void setInject() {
        // 没注入 会 导入编译失败 找不到DaggerAppComponent
        getActivityComponent().inject(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void init() {
//        presenter.loadReadDetail(0);
//        presenter.loadMovieDetail(0);
        initAnim();
        initToolbar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mContentListBean = (ContentListBean) getIntent().getSerializableExtra(Constants.ONE_LIST_BEAN);
        tvTitle.setText(mContentListBean.getShareList().getWx().getTitle().split("\\|")[0].
                trim());
        tvDetailTitle.setText(mContentListBean.getTitle());
        tvUserName.setText(mContentListBean.getShareList().getWx().getDesc().split(" ")[0].trim());

        tvLikeNum.setText(mContentListBean.getLikeCount() + "");

        initWebView();
        presenter.loadDetail(mContentListBean);
        // 评论栏状态动画监听
        initListener();
    }

    // 评论栏状态动画监听
    private void initListener() {
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY - oldScrollY > 0 && mIsBottomShow) { // 下移隐藏
                    mIsBottomShow = false;
                    layoutBottom2.animate().translationY(layoutBottom2.getHeight());
                    tvTitle.setText(tvDetailTitle.getText().toString());
                } else if (scrollY - oldScrollY < 0 && !mIsBottomShow) { // 上移出现
                    mIsBottomShow = true;
                    layoutBottom2.animate().translationY(0);

                }

                if (scrollY == 0 && mIsBottomShow) {
                    tvTitle.setText(mContentListBean.getShareList().getWx().getTitle().split(
                            "\\|")[0].trim());
                }
            }
        });
    }

    private void initAnim() {
        ivLoading.setImageResource(R.drawable.web_view_loading);
        mAnimationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        mAnimationDrawable.start();
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
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                stopAnim();
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
        showContent(readDetailBean.getHpContent(),readDetailBean.getHpAuthorIntroduce() + " " + readDetailBean.getEditorEmail(),readDetailBean.getAuthor().get(0));
    }

    @SuppressLint("SetTextI18n")
    private void showContent(String hpContent, String sIntroduce, AuthorBean authorBean) {
        List<String> list = new ArrayList<>();
        list.add(Constants.ONE_DETAIL_CSS);
        List<String> list1 = new ArrayList<>();
        list1.add(Constants.ONE_DETAIL_JS1);
        list1.add(Constants.ONE_DETAIL_JS2);
        list1.add(Constants.ONE_DETAIL_JS3);

        String htmlData = HtmlUtil.createHtmlData(hpContent, list, list1);
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

        tvIntroduce.setText(sIntroduce);

        Utils.displayImage(this, authorBean.getWebUrl(),
                ivAuthor, Utils.getImageOptions(R.mipmap.ic_launcher_round, 360));
        tvHpAuthor.setText(authorBean.getUserName() + " " + authorBean.getWbName());
        layoutBottom.setVisibility(View.VISIBLE);
        tvAuthIt.setText(authorBean.getDesc());
    }


    private void stopAnim() {
        mAnimationDrawable.stop();
        layoutBottom.setVisibility(View.VISIBLE);
        ivLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMovieData(MovieDetailBean movieDetailBean) {
        MovieDetailBean.DataBean dataBean = movieDetailBean.getData().get(0);
        showContent(dataBean.getContent(),dataBean.getChargeEdt() + " " + dataBean.getEditorEmail(),dataBean.getUser());
    }

    @Override
    public void showMusicData(MusicDetailBean musicDetailBean) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showReadComment(CommentBean commentBean) {
        mCommentAdapter = new CommentAdapter(this, commentBean);
        recyclerView.setAdapter(mCommentAdapter);
        tvCommentNum.setText(commentBean.getCount() + "");

    }
}