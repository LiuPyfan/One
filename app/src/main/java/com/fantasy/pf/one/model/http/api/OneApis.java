package com.fantasy.pf.one.model.http.api;



import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;
import com.fantasy.pf.one.model.http.response.MyHttpResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by KomoriWu
 * on 2017/9/15.
 */

public interface OneApis {

    //android
    String HOST = "http://v3.wufazhuce.com:8000/api/";
    //详情结尾
    String END = "?cchannel=wdj&version=4.3.4&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";


    //获取最新 idList
    @GET("onelist/idlist" + END)
    Flowable<OneIdBean> getOneId();
    //    Flowable<OneIdBean> getOneIdList();

    //获取 onelist
    @GET("onelist/{id}/0" + END)
    Flowable<MyHttpResponse<OneListBean>> getOneList(@Path("id") String id);

    // 获取getOneList()里的item_id
    //获取文章详细信息
    @GET("essay/{item_id}" + END)
    Flowable<MyHttpResponse<ReadDetailBean>> getReadDetail(@Path("item_id") String itemId);

//    //获取文章的评论信息
//    @GET("comment/praiseandtime/essay/{item_id}/0" + END)
//    Flowable<MyHttpResponse<CommentBean>> geReadCommentDetail(@Path("item_id") String itemId);
//
    //获取影视详细信息
    @GET("movie/{item_id}/story/1/0" + END)
    Flowable<MyHttpResponse<MovieDetailBean>> getMovieDetail(@Path("item_id") String itemId);

//    //获取音乐详细信息
//    @GET("music/detail/{item_id}" + END)
//    Flowable<MyHttpResponse<MusicDetailBean>> geMusicDetail(@Path("item_id") String itemId);
//
//    //获取问答的详细信息
//    @GET("question/{item_id}" + END)
//    Flowable<MyHttpResponse<QuestionDetailBean>> geQuestionDetail(@Path("item_id") String itemId);
//
//    //获取影视的photo
//    @GET("movie/detail/{item_id}" + END)
//    Flowable<MyHttpResponse<MoviePhotoBean>> geMoviePhoto(@Path("item_id") String itemId);
}
