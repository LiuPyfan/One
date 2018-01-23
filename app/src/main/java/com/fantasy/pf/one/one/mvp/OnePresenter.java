package com.fantasy.pf.one.one.mvp;

import android.util.Log;

import com.fantasy.pf.one.base.RxPresenter;
import com.fantasy.pf.one.model.DataManagerModel;
import com.fantasy.pf.one.model.bean.OneIdBean;
import com.fantasy.pf.one.utils.RxUtil;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public class OnePresenter extends RxPresenter<OneContract.View> implements OneContract.Presenter {
    private static final String TAG = "OnePresenter";
    private DataManagerModel mDataManagerModel;


    @Inject
    public OnePresenter(DataManagerModel dataManagerModel) {
        mDataManagerModel = dataManagerModel;
    }

    @Override
    public void getOneIdList() {
        addSubscribe(
                mDataManagerModel.fetchOneIdList()
                        .compose(RxUtil.<OneIdBean>rxSchedulerHelper())
                        .flatMap(new Function<OneIdBean, Publisher<String>>() {//flatMap 返回的是一个 Flowable
                            @Override
                            public Publisher<String> apply(OneIdBean oneIdBean) throws Exception {
                                List<String> strings = oneIdBean.getData();
                                return Flowable.fromIterable(strings);//可以接收一个 Iterable 容器作为输入,每次发射一个元素
                            }
                        })
                        .subscribeWith(new ResourceSubscriber<String>(){

                            @Override
                            public void onNext(String s) {
                                Log.d("OnePresenter", s);
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d(TAG, t.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        })
//                        .map(new Function<OneIdBean, OneIdBean>() { //就变换 Flowable 然后返回一个指定类型的 Flowable 对象(可以返回任意类型的 Flowable)
//                            @Override
//                            public OneIdBean apply(OneIdBean oneIdBean) throws Exception {
//                                return oneIdBean;
//                            }
//
//
//                        })
//                        .subscribe(new Consumer<OneIdBean>() {
//                                       @Override
//                                       public void accept(OneIdBean oneIdBean) throws Exception {
//                                           Log.d(TAG, oneIdBean.toString());
//                                       }
//                                   }, new Consumer<Throwable>() {
//                                       @Override
//                                       public void accept(Throwable throwable) throws Exception {
//                                           Log.d(TAG, throwable.toString());
//                                       }
//                                   }
//                        )

        );

    }
}
