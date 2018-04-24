package com.example.zqd.myproject.presenter.activity;

import com.example.zqd.myproject.base.BasePresenter;
import com.example.zqd.myproject.contract.HistoryActivityContract;
import com.example.zqd.myproject.model.api.HandleObserver;
import com.example.zqd.myproject.model.api.ServiceManager;
import com.example.zqd.myproject.model.bean.HistoryBean;
import com.example.zqd.myproject.utils.RxUtil;

import javax.inject.Inject;

/**
 * <p>Title: com.example.zqd.myproject.presenter.activity</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/24 13:29
 */
public class HistoryActivityPresenter extends BasePresenter<HistoryActivityContract.View> implements HistoryActivityContract.Presenter {
    /**
     * PS:注入当前构造器
     *
     * @param serviceManager
     */
    @Inject
    public HistoryActivityPresenter(ServiceManager serviceManager) {
        super(serviceManager);
    }

    @Override
    public void getData() {
        mApi.getHistory("57858", "b91d7fef2583441c8a5741bf38941e7b")
                .compose(RxUtil.<HistoryBean>applySchedulersWithLoading(mView))
                .subscribe(new HandleObserver<HistoryBean>() {
                    @Override
                    protected void onMyError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onNext(HistoryBean historyBean) {
                        mView.showData(historyBean.getShowapi_res_body().getList());
                    }
                });
    }
}
