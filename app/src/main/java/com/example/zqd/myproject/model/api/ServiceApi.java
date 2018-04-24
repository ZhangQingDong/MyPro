package com.example.zqd.myproject.model.api;

import com.example.zqd.myproject.model.bean.HistoryBean;
import com.example.zqd.myproject.model.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <p>Title: com.example.zqd.myproject.model.api</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 9:13
 */
public interface ServiceApi {

    @FormUrlEncoded
    @POST("/app_login.htm")
    Observable<LoginBean> getAppLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    /**
     * 历史上的今天
     */
    @FormUrlEncoded
    @POST("/119-42")
    Observable<HistoryBean> getHistory(
            @Field("showapi_appid") String showapi_appid,
            @Field("showapi_sign") String showapi_sign
    );

}
