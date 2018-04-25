package com.example.zqd.myproject.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zqd.myproject.constancs.RxBusConstants;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.dagger2.component.DaggerActivityComponent;
import com.example.zqd.myproject.ui.dialog.ProgressDialog;
import com.example.zqd.myproject.utils.RxBus;
import com.example.zqd.myproject.utils.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

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
    protected Observable mObservable;
    protected Disposable mDisposable;

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
        registRxBus();
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
        progressDialog = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mObservable != null) {
            RxBus.getInstance().unregister(getRxTag(), mObservable);
        }
    }

    /**
     * 注册rxbus
     */
    protected void registRxBus() {

    }

    /**
     * 获取RXBUS注册的TAG
     *
     * @return
     */
    protected String getRxTag() {
        return "";
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

    /**
     * activity跳转
     *
     * @param activity
     * @param cls
     * @param bundle
     */
    protected void go(RxAppCompatActivity activity, Class<? extends RxAppCompatActivity> cls, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        doAnim();
    }

    /**
     * 回传跳转
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void goForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        doAnim();
    }

    /**
     * 退出当前activity
     */
    protected void out() {
        finish();
        doAnim();
    }

    /**
     * activity动画的执行
     */
    private void doAnim() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
