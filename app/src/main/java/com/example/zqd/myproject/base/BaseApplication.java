package com.example.zqd.myproject.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.zqd.greendao.DaoMaster;
import com.example.zqd.greendao.DaoSession;
import com.example.zqd.myproject.dagger2.component.AppComponent;
import com.example.zqd.myproject.dagger2.component.DaggerAppComponent;
import com.example.zqd.myproject.dagger2.module.AppModule;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

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

    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase writableDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
        initLeakCanary();
        CrashReport.initCrashReport(this, "1a9d6e4309", false);
        initDB();
    }

    /**
     * greenDao init
     */
    private void initDB() {
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "zqd-db", null);
        writableDatabase = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
    }

    public static BaseApplication getApp() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化LeakCanary
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
