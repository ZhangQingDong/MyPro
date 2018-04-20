package com.example.zqd.myproject.dagger2.component;

import com.example.zqd.myproject.dagger2.module.AppModule;
import com.example.zqd.myproject.model.api.ServiceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <p>Title: com.example.zqd.myproject.dagger2.component</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 9:46
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    ServiceManager proServiceManager();
}
