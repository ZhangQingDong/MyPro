package com.example.zqd.myproject.presenter.activity;

import com.example.zqd.myproject.base.BasePresenter;
import com.example.zqd.myproject.contract.MainActivityContract;
import com.example.zqd.myproject.model.api.HandleObserver;
import com.example.zqd.myproject.model.api.ServiceApi;
import com.example.zqd.myproject.model.api.ServiceManager;
import com.example.zqd.myproject.model.bean.LoginBean;
import com.example.zqd.myproject.utils.RxUtil;

import javax.inject.Inject;

/**
 * <p>Title: com.example.zqd.myproject.presenter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 16:18
 */
public class MainActivityPresenter extends BasePresenter<MainActivityContract.View> implements MainActivityContract.Presenter {

    /**
     * PS:注入当前构造器
     *
     * @param serviceManager
     */
    @Inject
    public MainActivityPresenter(ServiceManager serviceManager) {
        super(serviceManager);
    }

    @Override
    public void getDoLogin(String userName, String passWord) {
        mApi.getAppLogin(userName, passWord)
                .compose(RxUtil.<LoginBean>applySchedulersWithLoading(mView))
                .subscribe(new HandleObserver<LoginBean>() {
                    @Override
                    protected void onMyError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.showLoginSuccess(loginBean);
                    }
                });
    }

}
