package com.example.zqd.myproject.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.BaseActivity;
import com.example.zqd.myproject.constancs.RxBusConstants;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.contract.MainActivityContract;
import com.example.zqd.myproject.model.bean.LoginBean;
import com.example.zqd.myproject.presenter.activity.MainActivityPresenter;
import com.example.zqd.myproject.ui.dialog.NoticeDialog;
import com.example.zqd.myproject.utils.ImageUtil;
import com.example.zqd.myproject.utils.RxBus;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
        out();
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

    @OnClick({R.id.bt, R.id.tab_fragment, R.id.bt_history_today, R.id.bt_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt:
                presenter.getDoLogin("XXXX", "XXXX");
                break;
            case R.id.tab_fragment:
                startActivity(new Intent(MainActivity.this, TabActivity.class));
                break;
            case R.id.bt_history_today:
                go(MainActivity.this, HistoryActivity.class, null);
                break;
            case R.id.bt_dialog:
                new NoticeDialog(MainActivity.this).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void registRxBus() {
        mObservable = RxBus.getInstance().register(RxBusConstants.TEST_TAG);
        mDisposable = mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                iv.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            }
        });
    }

    @Override
    protected String getRxTag() {
        return RxBusConstants.TEST_TAG;
    }

}
