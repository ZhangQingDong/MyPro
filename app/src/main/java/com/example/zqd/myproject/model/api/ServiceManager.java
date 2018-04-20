package com.example.zqd.myproject.model.api;

/**
 * <p>Title: com.example.zqd.myproject.model.api</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 9:14
 */
public class ServiceManager {

    private final ServiceApi mServiceApi;

    public ServiceManager(ServiceApi serviceApi) {
        this.mServiceApi = serviceApi;
    }

    public ServiceApi getServiceApi() {
        return mServiceApi;
    }

}
