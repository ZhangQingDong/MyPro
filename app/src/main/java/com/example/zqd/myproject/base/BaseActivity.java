package com.example.zqd.myproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.dagger2.component.DaggerActivityComponent;
import com.example.zqd.myproject.ui.dialog.ProgressDialog;
import com.example.zqd.myproject.utils.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>Title: com.example.zqd.myproject.base</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/19 15:52
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    @Inject
    protected P presenter;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    /**
     * 初始化BaseActivity
     */
    private void initComponent() {
        initInject(getComponent());
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        if (presenter != null) {
            presenter.onAttach(this);
        }
        init();
    }

    private ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getApp().getAppComponent())
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter != null) {
            presenter.onDestroy();
        }
    }


    /**
     * 设置layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 返回键事件
     */
    protected abstract void onBackPress();

    /**
     * 子Acticity入口
     */
    protected abstract void init();

    /**
     * inject初始化
     *
     * @param component
     */
    protected abstract void initInject(ActivityComponent component);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackPress();
    }

    /**
     * 显示loading
     */
    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
    }

    /**
     * 隐藏loading
     */
    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String content) {
        ToastUtil.showShort(content);
    }

    @Override
    public void getEmptyData() {

    }

    @Override
    public void getErrorData() {

    }

}
