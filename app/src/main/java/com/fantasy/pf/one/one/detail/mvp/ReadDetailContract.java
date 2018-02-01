package com.fantasy.pf.one.one.detail.mvp;

import com.fantasy.pf.one.base.BasePresenter;
import com.fantasy.pf.one.base.BaseView;
import com.fantasy.pf.one.model.bean.ContentListBean;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/1/30.
 * 包名:com.fantasy.pf.one.one.detail.mvp
 */

public class ReadDetailContract {
    public interface View extends BaseView {
        void showContent(String content);
    }

    public interface Presenter extends BasePresenter<View> {
//        void loadReadDetail(int position);
//        void loadMovieDetail(int position);
        void loadReadDetail(String itemId);

        void loadMovieDetail(String itemId);

        void loadMusicDetail(String itemId);

        void loadDetail(ContentListBean contentListBean);

    }
}
