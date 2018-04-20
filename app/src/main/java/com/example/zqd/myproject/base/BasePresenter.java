package com.example.zqd.myproject.base;

import android.view.View;

import com.example.zqd.myproject.model.api.ServiceApi;
import com.example.zqd.myproject.model.api.ServiceManager;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 16:24
 */
public class BasePresenter<V extends BaseView> {

    protected V mView = null;

    protected ServiceApi mApi;

    /**
     * PS:注入当前构造器
     *
     * @param serviceManager
     */
    public BasePresenter(ServiceManager serviceManager) {
        this.mApi = serviceManager.getServiceApi();
    }

    /**
     * Activity绑定View
     *
     * @param view
     */
    void onAttach(V view) {
        mView = view;
    }

    /**
     * 销毁置空
     */
    void onDestroy() {
        mView = null;
        mApi = null;
    }

}
