package com.example.zqd.myproject.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/26 11:00
 */
public abstract class BaseDialog extends Dialog {

    protected Context context;
    protected View mRootView;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        mRootView = View.inflate(context, getLayoutRes(), null);
        setContentView(mRootView);
        getInitView();
        setWindow();

    }

    protected void setWindow() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 0.9);
        dialogWindow.setAttributes(lp);
    }

    public abstract int getLayoutRes();

    public abstract void getInitView();

}
