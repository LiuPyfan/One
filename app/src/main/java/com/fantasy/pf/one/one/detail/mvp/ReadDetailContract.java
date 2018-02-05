package com.fantasy.pf.one.one.detail.mvp;

import com.fantasy.pf.one.base.BasePresenter;
import com.fantasy.pf.one.base.BaseView;
import com.fantasy.pf.one.model.bean.CommentBean;
import com.fantasy.pf.one.model.bean.ContentListBean;
import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.MusicDetailBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/1/30.
 * 包名:com.fantasy.pf.one.one.detail.mvp
 */

public class ReadDetailContract {
    public interface View extends BaseView {
        //        void showContent(String content);
        void showReadData(ReadDetailBean readDetailBean);

        void showMovieData(MovieDetailBean readDetailBean);

        void showMusicData(MusicDetailBean readDetailBean);
        // 二级界面 评论的数据
        void showReadComment(CommentBean commentBean);
    }

    public interface Presenter extends BasePresenter<View> {
        //        void loadReadDetail(int position);
//        void loadMovieDetail(int position);
        void loadReadDetail(String itemId);

        void loadMovieDetail(String itemId);

        void loadMusicDetail(String itemId);

        void loadDetail(ContentListBean contentListBean);

        void loadReadComment(String itemId);

    }
}
