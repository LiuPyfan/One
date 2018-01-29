package com.fantasy.pf.one.one.mvp;

import com.fantasy.pf.one.base.BasePresenter;
import com.fantasy.pf.one.base.BaseView;
import com.fantasy.pf.one.model.DataManagerModel;
import com.fantasy.pf.one.model.bean.OneListBean;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/20.
 * let none that wait on thee be ashamed
 */

public interface OneContract {
    interface View extends BaseView{
        void showRefresh();
        void hideRefresh();
        void refreshData(OneListBean oneListBean);
    }

    interface Presenter extends BasePresenter<View>{
//        void  getOneIdList();
//        void getOneList(LoadOneListData listData);
        void  loadOneList(int page);
    }


}
