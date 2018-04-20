package com.example.zqd.myproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.example.zqd.myproject.R;

/**
 * <p>Title: com.example.zqd.myproject.ui.dialog</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 13:38
 */
public class ProgressDialog extends Dialog {

    private Context context;
    private ProgressBar progressBar;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.CumstomDialog);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.dialog_progress, null);
        setContentView(view);
    }

}
