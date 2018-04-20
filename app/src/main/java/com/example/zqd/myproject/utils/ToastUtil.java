package com.example.zqd.myproject.utils;

import android.widget.Toast;

import com.example.zqd.myproject.base.BaseApplication;

/**
 * <p>Title: com.example.zqd.myproject.utils</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 17:38
 */
public class ToastUtil {

    private static Toast toast = null;

    private static Toast initToast(CharSequence content, int duration) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.application, content, duration);
        } else {
            toast.setText(content);
            toast.setDuration(duration);
        }
        return toast;
    }

    /**
     * 短时间显示Toast
     *
     * @param content
     */
    public static void showShort(CharSequence content) {
        initToast(content, Toast.LENGTH_SHORT).show();
    }
}
