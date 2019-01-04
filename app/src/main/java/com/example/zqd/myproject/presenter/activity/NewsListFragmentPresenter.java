package com.example.zqd.myproject.presenter.activity;

import com.example.zqd.myproject.base.BasePresenter;
import com.example.zqd.myproject.contract.NewsListFragmentContract;
import com.example.zqd.myproject.model.api.HandleObserver;
import com.example.zqd.myproject.model.api.ServiceManager;
import com.example.zqd.myproject.model.bean.NewsListBean;
import com.example.zqd.myproject.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListFragmentPresenter extends BasePresenter<NewsListFragmentContract.View> implements NewsListFragmentContract.Presenter {

    /**
     * PS:注入当前构造器
     *
     * @param serviceManager
     */
    @Inject
    public NewsListFragmentPresenter(ServiceManager serviceManager) {
        super(serviceManager);
    }

    @Override
    public void getData(String id, String action, int pullNum) {
        mApi.getNews(id, action, pullNum)
                .compose(RxUtil.<List<NewsListBean>>applySchedulersWithLoadingFragment(mView))
                .subscribe(new HandleObserver<List<NewsListBean>>() {
                    @Override
                    public void onNext(List<NewsListBean> listBeans) {
                        if (listBeans != null && !listBeans.isEmpty()) {
                            List<NewsListBean.ItemBean> itemBeanList = new ArrayList<>();
                            for (int i = 0; i < listBeans.size(); i++) {
                                for (int j = 0; j < listBeans.get(i).getItem().size(); j++) {
                                    itemBeanList.add(listBeans.get(i).getItem().get(j));
                                }
                            }
                            mView.showData(itemBeanList);
                        }
                    }

                    @Override
                    protected void onMyError(Throwable e) {
                        e.getMessage();
                        mView.getErrorData();
                    }
                });
    }
}
