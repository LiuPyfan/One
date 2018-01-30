package com.fantasy.pf.one.one.detail.mvp;

import android.util.Log;

import com.fantasy.pf.one.base.RxPresenter;
import com.fantasy.pf.one.model.DataManagerModel;
import com.fantasy.pf.one.model.bean.MovieDetailBean;
import com.fantasy.pf.one.model.bean.ReadDetailBean;
import com.fantasy.pf.one.model.http.CommonSubscriber;
import com.fantasy.pf.one.model.http.response.MyHttpResponse;
import com.fantasy.pf.one.utils.RxUtil;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/1/30.
 * 包名:com.fantasy.pf.one.one.detail.mvp
 */

public class ReadDetailPresenter extends RxPresenter<ReadDetailContract.View> implements ReadDetailContract.Presenter {
    private static final String TAG = "ReadDetailPresenter";
    private DataManagerModel mDataManagerModel;
    // 无注解会找不到DaggerAppComponent,编译失败
    @Inject
    public ReadDetailPresenter(DataManagerModel dataManagerModel) {
        mDataManagerModel = dataManagerModel;
    }

    @Override
    public void loadReadDetail(int position) {
        addSubscribe(mDataManagerModel.getReadDetail("2897")
                .compose(RxUtil.<MyHttpResponse<ReadDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<ReadDetailBean>, Publisher<ReadDetailBean>>() {
                    @Override
                    public Publisher<ReadDetailBean> apply(MyHttpResponse<ReadDetailBean> httpResponse) throws Exception {
                        return Flowable.just(httpResponse.getData());
                    }
                })
                .subscribeWith(new CommonSubscriber<ReadDetailBean>(view){

                    @Override
                    public void onNext(ReadDetailBean readDetailBean) {
                        view.showContent(readDetailBean.getHpContent());
                        Log.d(TAG, readDetailBean.getHpContent());
                    }
                })

        );
    }

    @Override
    public void loadMovieDetail(int position) {
        addSubscribe(mDataManagerModel.getMovieDetail("1220")
                .compose(RxUtil.<MyHttpResponse<MovieDetailBean>>rxSchedulerHelper())
                .flatMap(new Function<MyHttpResponse<MovieDetailBean>, Publisher<MovieDetailBean>>() {
                    @Override
                    public Publisher<MovieDetailBean> apply(MyHttpResponse<MovieDetailBean> response) throws Exception {
                        return Flowable.just(response.getData());
                    }
                })
                .subscribeWith(new CommonSubscriber<MovieDetailBean>(view){

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        view.showContent(movieDetailBean.getData().get(0).getContent());
                        Log.d(TAG,movieDetailBean.getData().get(0).getContent());
                    }
                })

        );
    }
}
