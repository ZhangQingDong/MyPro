package com.example.zqd.myproject.base;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 16:25
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showToast(String content);

    void getEmptyData();

    void getErrorData();

}
