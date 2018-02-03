package com.fantasy.pf.one.one;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fantasy.pf.one.R;
import com.fantasy.pf.one.base.MvpBaseFragment;
import com.fantasy.pf.one.main.MainActivity;
import com.fantasy.pf.one.model.bean.ContentListBean;
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.one.detail.ReadDetailActivity;
import com.fantasy.pf.one.one.mvp.LoadOneListData;
import com.fantasy.pf.one.one.mvp.OneContract;
import com.fantasy.pf.one.one.mvp.OnePresenter;
import com.fantasy.pf.one.utils.Constants;
import com.fantasy.pf.one.utils.Utils;
import com.fantasy.pf.one.widget.listener.HidingScrollBottomListener;
import com.fantasy.pf.one.widget.refresh.RefreshLayout;
import com.fantasy.pf.one.widget.refresh.SwipeRefreshLayoutDirection;

import org.reactivestreams.Publisher;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends MvpBaseFragment<OnePresenter> implements RefreshLayout.OnRefreshListener, OneContract.View, OneAdapter.OnItemClickListener {
    //
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    private LinearLayoutManager mLayoutManager;
    private OneAdapter mOneAdapter;
    private int mPage;

    private OneListBean mOneListBean;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, null);

    }

    @Override
    public void init() {

//        presenter.getOneIdList();

        refreshLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorScheme(R.color.main_text_color, R.color.tv_hint,
                R.color.line_color);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        presenter.getOneList(new LoadOneListData() {
//            @Override
//            public void onSuccess(OneListBean oneListBean) {
//                Log.d("OneFragment", oneListBean.getId());
//
//                mOneAdapter = new OneAdapter(oneListBean,getActivity());
//                mRecyclerView.setAdapter(mOneAdapter);
//            }
//        });

        mOneAdapter = new OneAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mOneAdapter);
        onRefresh(SwipeRefreshLayoutDirection.TOP);

        initListener();

    }

    private void initListener() {
        mRecyclerView.addOnScrollListener(new HidingScrollBottomListener() {
            @Override
            public void onHide() {
                ((MainActivity) getActivity()).changeRadioGState(false);
                ((MainActivity) getActivity()).setToolBarWeatherState(false);

            }

            @Override
            public void onShow() {
                ((MainActivity) getActivity()).changeRadioGState(true);
                ((MainActivity) getActivity()).setToolBarWeatherState(true);
            }

            @Override
            public void onUpdateDate() {
                Flowable.just(Utils.getCurrentViewIndex(mLayoutManager))
                        .flatMap(new Function<Integer, Publisher<String>>() {
                            @Override
                            public Publisher<String> apply(Integer integer) throws Exception {
                                // Log.d("OneFragment", "integer:" + integer);
                                 Log.d("OneAdapter", "integer:" + integer);
                                return Flowable.just(mOneAdapter.getDate(integer));
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                ((MainActivity) getActivity()).setToolBarTitle(s);

                            }
                        });
            }
        });
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    // onRefresh接口
    @Override
    public void onRefresh(SwipeRefreshLayoutDirection direction) {
        if (direction == SwipeRefreshLayoutDirection.TOP) {
            mPage = 0;
            refreshLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
        } else {
            mPage++;
        }
        presenter.loadOneList(mPage);
    }

    @Override
    public void showRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void refreshData(OneListBean oneListBean) {
        mOneListBean = oneListBean;
        mOneAdapter.addOneListData(oneListBean, mPage == 0);
        // 获取标题头天气
        OneListBean.WeatherBean weatherBean = oneListBean.getWeather();

        // ((MainActivity)getActivity()).setToolBarTitle(oneListBean.getDate().split(" ")[0].replace("-","<font color='#878787'> / </font>"));//2018-02-01 06:00:00
        //((MainActivity) getActivity()).setToolBarWeather(weatherBean.getCityName() + "  " + weatherBean.getClimate() + "  " + weatherBean.getTemperature()+"℃");


        if (mPage == 0) {
            ((MainActivity) getActivity()).setToolBarTitle(Utils.formatDate(oneListBean.getDate()));
            ((MainActivity) getActivity()).setToolBarWeather(weatherBean.getCityName() +
                    "  " + weatherBean.getClimate() + "  " + weatherBean.getTemperature() + "℃");
        }
    }

    // 滑到顶端 aty中点击rb执行此方法
    public void scrollToTop() {
        mLayoutManager.scrollToPositionWithOffset(0, 0);
        mLayoutManager.setStackFromEnd(true);
    }

    @Override
    public void onItemClick(int position) {
        ContentListBean contentListBean = mOneAdapter.getContentList().get(position);
        switch(Integer.parseInt(contentListBean.getCategory())){
            case Constants.CATEGORY_REPORTER:
                ((MainActivity)getActivity()).showPopup(contentListBean);
                break;

            default:
                Intent intent = new Intent();
                intent.setClass(getActivity(),ReadDetailActivity.class);
                intent.putExtra(Constants.ONE_LIST_BEAN, mOneListBean.getContentList().get(position));
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onDestroy() {
        if (presenter.view != null)presenter.view = null;
        super.onDestroy();
    }
}
