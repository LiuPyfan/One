package com.fantasy.pf.one.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/19.
 * let none that wait on thee be ashamed
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    public T view;
    // 管理所有的observer
    private CompositeDisposable mCompositeDisposable;

    // 防侧漏
    private void unSubscribe(){
        if (mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
    }
    /**Disposable
     * @param subscription
     * @date 创建时间 2018/1/19 下午9:32
     * @author pforlove
     * @description void dispose();  // ends a subscription 结束订阅
     * boolean isDisposed(); // 如果资源被处理了返回真（未订阅）
     * returns true if resource is disposed (unsubscribed)
     * Disposable是一个接口
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }
}
