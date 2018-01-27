package com.fantasy.pf.one.one;


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
import com.fantasy.pf.one.model.bean.OneListBean;
import com.fantasy.pf.one.one.mvp.LoadOneListData;
import com.fantasy.pf.one.one.mvp.OneContract;
import com.fantasy.pf.one.one.mvp.OnePresenter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends MvpBaseFragment<OnePresenter> {
//
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private OneAdapter mOneAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,null);

    }

    @Override
    public void init() {

//        presenter.getOneIdList();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.getOneList(new LoadOneListData() {
            @Override
            public void onSuccess(OneListBean oneListBean) {
                Log.d("OneFragment", oneListBean.getId());

                mOneAdapter = new OneAdapter(oneListBean,getActivity());
                mRecyclerView.setAdapter(mOneAdapter);
            }
        });

    }


    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
