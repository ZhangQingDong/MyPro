package com.example.zqd.myproject.dagger2.module;

import com.example.zqd.myproject.constancs.Constants;
import com.example.zqd.myproject.network.OkHttpConfig;
import com.example.zqd.myproject.model.api.ServiceApi;
import com.example.zqd.myproject.model.api.ServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <p>Title: com.example.zqd.myproject.dagger2.module</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 9:45
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient).build();
        return retrofit;
    }

    @Singleton
    @Provides
    public ServiceManager provideServiceManager(Retrofit retrofit) {
        ServiceManager serviceManager = new ServiceManager(retrofit.create(ServiceApi.class));
        return serviceManager;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttp() {
        OkHttpConfig config = OkHttpConfig.Builder().build();
        return config.httpClient;
    }

}
