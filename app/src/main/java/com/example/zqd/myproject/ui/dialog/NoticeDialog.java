package com.example.zqd.myproject.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.BaseDialog;

/**
 * <p>Title: com.example.zqd.myproject.ui.dialog</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/26 11:22
 */
public class NoticeDialog extends BaseDialog {

    public NoticeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_notice;
    }

    @Override
    public void getInitView() {

    }
}
