package com.example.zqd.myproject.utils;

import android.content.Context;

/**
 * <p>Title: com.example.zqd.myproject.utils</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/23 14:16
 */
public final class DpUtils {

    public static int dp2px(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
