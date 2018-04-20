package com.example.zqd.myproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zqd.myproject.dagger2.component.DaggerFragmentComponent;
import com.example.zqd.myproject.dagger2.component.FragmentComponent;
import com.example.zqd.myproject.ui.dialog.ProgressDialog;
import com.example.zqd.myproject.utils.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;

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
 * @date 2018/4/20 14:17
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView {

    @Inject
    P presenter;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), null);
        return mRootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initArguments(getArguments());
    }

    protected void initArguments(Bundle arguments) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        if (presenter != null) {
            presenter.onAttach(this);
        }
        initInject(getComponent());
        init();
    }

    /**
     * 设置layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 子Fragment入口
     */
    protected abstract void init();

    private FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getApp().getAppComponent())
                .build();
    }

    /**
     * inject初始化
     *
     * @param component
     */
    protected abstract void initInject(FragmentComponent component);

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.show();
    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
