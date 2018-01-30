package com.fantasy.pf.one.widget.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/1/29.
 * 包名:com.fantasy.pf.one.widget.listener
 */

public abstract class HidingScrollBottomListener extends RecyclerView.OnScrollListener {


    private static final int HIDE_THRESHOLD = 20;//滑动隐藏的阈值

    private int mScrolledDistance = 0;//滑动距离
    private boolean mControlsVisible = true;//控件的显示状态

    /**
     * 向下滑动隐藏控件, 向上滑动显示控件
     * dy, 手指向下滑动为负值, 手指向上滑动为正值
     *
     dx > 0 时为手指向左滚动,列表滚动显示右面的内容
     dx < 0 时为手指向右滚动,列表滚动显示左面的内容
     dy > 0 时为手指向上滚动,列表滚动显示下面的内容
     dy < 0 时为手指向下滚动,列表滚动显示上面的内容
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 必须是LinearLayoutManager，定义第一条item
        int firestVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        // item == 0 说明已经滑动到最顶端
        if (firestVisibleItem == 0) {
            if (!mControlsVisible) {
                // 如果控件状态不可见
                /**
                 *  onShow，将控件状态置为可见
                 */
                onShow();
                mControlsVisible = true;
            }
        } else {
            // item != 0 当前Item不是第一条
            /**
             * 此时分向上滑动，向下滑动两种状态
             */
            if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) { // 滑动距离超过阈值且空间此时可见
                onHide();// 隐藏
                mControlsVisible = false;

            }else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible){
                onShow();
                mControlsVisible = true;

            }
            mScrolledDistance = 0;// 置0 ，防止继续滑动
        }

        //mControlsVisible && dy>0 控件可见 手指向上滑 界面向下滑
        //!mControlsVisible && dy<0 控件不可见 手指向下滑 界面向上滑

        // 实时改变mScrolledDistance的值
        if((mControlsVisible && dy>0) || (!mControlsVisible && dy<0)) {
            mScrolledDistance += dy;
        }

    }

    public abstract void onHide();//在onHide()方法里面实现控件隐藏功能

    public abstract void onShow();//在onShow()方法里面实现控件显示功能
}
