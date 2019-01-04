package com.example.zqd.myproject.contract;

import com.example.zqd.myproject.base.BaseView;
import com.example.zqd.myproject.model.bean.NewsListBean;

import java.util.List;

public interface NewsListFragmentContract {
    interface View extends BaseView {
        void showData(List<NewsListBean.ItemBean> itemBeanList);
    }

    interface Presenter {
        void getData(String id, String action, int pullNum);
    }
}
