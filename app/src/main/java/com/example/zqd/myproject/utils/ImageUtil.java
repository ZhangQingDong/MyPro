package com.example.zqd.myproject.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.GlideApp;
import com.example.zqd.myproject.base.GlideRequest;

/**
 * <p>Title: com.example.zqd.myproject.utils</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 13:58
 */
public final class ImageUtil {
    public static void loadImage(Fragment fragment, Object object, ImageView imageView) {
        loadImage(fragment, object).centerCrop().into(imageView);
    }

    public static void loadImage(Activity activity, Object object, ImageView imageView) {
        loadImage(activity, object).centerCrop().into(imageView);
    }

    public static void loadImage(Context context, Object object, ImageView imageView) {
        loadImage(context, object).centerCrop().into(imageView);
    }

    public static void loadImage(Context context, Object object, ImageView imageView, DiskCacheStrategy arg0) {
        loadImageStrategy(context, object, arg0).centerCrop().into(imageView);
    }

    public static GlideRequest<Drawable> loadImage(Fragment fragment, Object object) {
        return GlideApp.with(fragment).load(object).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
    }

    public static GlideRequest<Drawable> loadImage(Activity activity, Object object) {
        return GlideApp.with(activity).load(object).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
    }

    public static GlideRequest<Drawable> loadImage(Context context, Object object) {
        return GlideApp.with(context).load(object).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
    }

    public static GlideRequest<Drawable> loadImageStrategy(Context context, Object object, DiskCacheStrategy arg0) {
        return GlideApp.with(context).load(object).diskCacheStrategy(arg0).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round);
    }
}
