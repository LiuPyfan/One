package com.fantasy.pf.one.model.http;

import com.fantasy.pf.one.model.bean.CommentBean;
import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.MusicDetailBean;
import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;
import com.fantasy.pf.one.model.http.response.MyHttpResponse;

import io.reactivex.Flowable;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 */

public interface HttpHelper {
//    Flowable<OneIdBean> fetchOneIdList();

    Flowable<OneIdBean> fetchOneId();

    Flowable<MyHttpResponse<OneListBean>> getOneList(String id);

    Flowable<MyHttpResponse<ReadDetailBean>>getReadDetail(String itemId);

    Flowable<MyHttpResponse<CommentBean>>getReadCommentDetail(String itemId);

    Flowable<MyHttpResponse<MovieDetailBean>> getMovieDetail(String itemId);

    Flowable<MyHttpResponse<MusicDetailBean>> getMusicDetail(String itemId);

}
