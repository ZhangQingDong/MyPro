package com.example.zqd.myproject.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.utils.DpUtils;

/**
 * <p>Title: com.example.zqd.myproject.ui.dialog</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/23 13:22
 */
public class CommonToolBar extends LinearLayout {

    private Context context;
    /**
     * 总体布局
     */
    private LinearLayout ll_all;

    /**
     * 左布局
     */
    private RelativeLayout rl_left;
    private ImageView iv_left;
    private TextView tv_left;

    /**
     * 右布局
     */
    private RelativeLayout rl_right;
    private ImageView iv_right;
    private TextView tv_right;

    /**
     * 中布局
     */
    private RelativeLayout rl_mid;
    private TextView tv_title;

    public CommonToolBar(Context context) {
        this(context, null);
    }

    public CommonToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CommonToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        addView(inflate(context, R.layout.layout_toolbar, null));
        getFindView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolBar);
        boolean isLeftShow = typedArray.getBoolean(R.styleable.CommonToolBar_isLeftShow, true);
        boolean isMidShow = typedArray.getBoolean(R.styleable.CommonToolBar_isMidShow, true);
        boolean isRightShow = typedArray.getBoolean(R.styleable.CommonToolBar_isRightShow, true);
        boolean isLeftIvShow = typedArray.getBoolean(R.styleable.CommonToolBar_isLeftIvShow, true);
        boolean isRightIvShow = typedArray.getBoolean(R.styleable.CommonToolBar_isRightIvShow, true);
        boolean isTitleShow = typedArray.getBoolean(R.styleable.CommonToolBar_isTitleShow, true);
        boolean isLeftTvShow = typedArray.getBoolean(R.styleable.CommonToolBar_isLeftTvShow, false);
        boolean isRightTvShow = typedArray.getBoolean(R.styleable.CommonToolBar_isRightTvShow, false);

        String title = typedArray.getString(R.styleable.CommonToolBar_title);

        setVisible(getRl_left(), isLeftShow);
        setVisible(getRl_mid(), isMidShow);
        setVisible(getRl_right(), isRightShow);
        setVisible(getIv_left(), isLeftIvShow);
        setVisible(getIv_right(), isRightIvShow);
        setVisible(getTv_title(), isTitleShow);
        setVisible(getTv_left(), isLeftTvShow);
        setVisible(getTv_right(), isRightTvShow);

        getTv_title().setText(title);
        setIvLeft(R.drawable.ic_arrow_back_black_24dp);
        int dp10 = DpUtils.dp2px(context, 10);
        getIv_left().setPadding(dp10, dp10, dp10, dp10);
    }

    /**
     * 设置左侧图标
     *
     * @param resource
     */
    public void setIvLeft(int resource) {
        getIv_left().setImageResource(resource);
    }

    /**
     * 找布局
     */
    private void getFindView() {
        ll_all = findViewById(R.id.ll_all);
        rl_left = findViewById(R.id.rl_left);
        iv_left = findViewById(R.id.iv_left);
        tv_left = findViewById(R.id.tv_left);
        rl_right = findViewById(R.id.rl_right);
        iv_right = findViewById(R.id.iv_right);
        tv_right = findViewById(R.id.tv_right);
        rl_mid = findViewById(R.id.rl_mid);
        tv_title = findViewById(R.id.tv_title);
    }

    /**
     * 设置GONE&VISSBLE
     *
     * @param targetView
     * @param flag
     * @return
     */
    public View setGone(View targetView, boolean flag) {
        if (flag) {
            targetView.setVisibility(View.GONE);
        } else {
            targetView.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置INVISSBLE&VISIBLE
     *
     * @param targetView
     * @param flag
     * @return
     */
    public View setVisible(View targetView, boolean flag) {
        if (flag) {
            targetView.setVisibility(View.VISIBLE);
        } else {
            targetView.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public LinearLayout getLl_all() {
        return ll_all;
    }

    public void setLl_all(LinearLayout ll_all) {
        this.ll_all = ll_all;
    }

    public RelativeLayout getRl_left() {
        return rl_left;
    }

    public void setRl_left(RelativeLayout rl_left) {
        this.rl_left = rl_left;
    }

    public ImageView getIv_left() {
        return iv_left;
    }

    public void setIv_left(ImageView iv_left) {
        this.iv_left = iv_left;
    }

    public TextView getTv_left() {
        return tv_left;
    }

    public void setTv_left(TextView tv_left) {
        this.tv_left = tv_left;
    }

    public RelativeLayout getRl_right() {
        return rl_right;
    }

    public void setRl_right(RelativeLayout rl_right) {
        this.rl_right = rl_right;
    }

    public ImageView getIv_right() {
        return iv_right;
    }

    public void setIv_right(ImageView iv_right) {
        this.iv_right = iv_right;
    }

    public TextView getTv_right() {
        return tv_right;
    }

    public void setTv_right(TextView tv_right) {
        this.tv_right = tv_right;
    }

    public RelativeLayout getRl_mid() {
        return rl_mid;
    }

    public void setRl_mid(RelativeLayout rl_mid) {
        this.rl_mid = rl_mid;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }
}
