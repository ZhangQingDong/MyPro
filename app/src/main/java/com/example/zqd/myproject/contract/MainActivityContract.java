package com.example.zqd.myproject.contract;

import com.example.zqd.myproject.base.BaseView;
import com.example.zqd.myproject.model.bean.LoginBean;

/**
 * <p>Title: com.example.zqd.myproject.contract</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 16:18
 */
public interface MainActivityContract {

    interface View extends BaseView {
        void showLoginSuccess(LoginBean loginBean);
    }

    interface Presenter {
        void getDoLogin(String userName, String passWord);
    }

}
