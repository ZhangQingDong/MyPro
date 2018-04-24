package com.example.zqd.myproject.ui.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.BaseActivity;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.contract.MainActivityContract;
import com.example.zqd.myproject.model.bean.LoginBean;
import com.example.zqd.myproject.presenter.activity.MainActivityPresenter;
import com.example.zqd.myproject.utils.ImageUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zqd
 */
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityContract.View {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBackPress() {
        this.finish();
    }

    @Override
    protected void init() {
        ImageUtil.loadImage(this, R.mipmap.ic_launcher, iv);
    }

    @Override
    protected void initInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void showLoginSuccess(LoginBean loginBean) {
        tv.setText(new Gson().toJson(loginBean));
    }

    @OnClick({R.id.bt, R.id.tab_fragment, R.id.bt_history_today})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt:
                presenter.getDoLogin("XXXX", "XXXX");
                break;
            case R.id.tab_fragment:
                startActivity(new Intent(MainActivity.this, TabActivity.class));
                break;
            case R.id.bt_history_today:
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                break;
            default:
                break;
        }
    }
}
