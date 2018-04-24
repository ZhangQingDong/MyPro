package com.example.zqd.myproject.contract;

import com.example.zqd.myproject.base.BaseView;
import com.example.zqd.myproject.model.bean.HistoryBean;

import java.util.List;

/**
 * <p>Title: com.example.zqd.myproject.contract</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/24 13:28
 */
public interface HistoryActivityContract {

    interface View extends BaseView {
        void showData(List<HistoryBean.ShowapiResBodyEntity.ListEntity> lists);
    }

    interface Presenter {
        void getData();
    }
}
