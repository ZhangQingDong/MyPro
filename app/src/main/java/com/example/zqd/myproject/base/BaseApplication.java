package com.example.zqd.myproject.base;

import android.app.Application;

import com.example.zqd.myproject.dagger2.component.AppComponent;
import com.example.zqd.myproject.dagger2.component.DaggerAppComponent;
import com.example.zqd.myproject.dagger2.module.AppModule;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 17:40
 */
public class BaseApplication extends Application {

    public static BaseApplication application = null;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public static BaseApplication getApp() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
