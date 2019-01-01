package com.example.zqd.myproject.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.BaseActivity;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.constancs.RxBusConstants;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.contract.MainActivityContract;
import com.example.zqd.myproject.model.bean.LoginBean;
import com.example.zqd.myproject.presenter.activity.MainActivityPresenter;
import com.example.zqd.myproject.ui.dialog.NoticeDialog;
import com.example.zqd.myproject.ui.view.fragment.FragmentFour;
import com.example.zqd.myproject.ui.view.fragment.FragmentOne;
import com.example.zqd.myproject.ui.view.fragment.FragmentThree;
import com.example.zqd.myproject.ui.view.fragment.FragmentTwo;
import com.example.zqd.myproject.utils.ContextUtils;
import com.example.zqd.myproject.utils.ImageUtil;
import com.example.zqd.myproject.utils.RxBus;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author zqd
 */
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityContract.View, View.OnClickListener {

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<ImageView> imageViewList = new ArrayList<>();
    private List<TextView> textViewList = new ArrayList<>();
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private FragmentManager fragmentManager;

    @BindView(R.id.ll_one)
    LinearLayout ll_one;
    @BindView(R.id.ll_two)
    LinearLayout ll_two;
    @BindView(R.id.ll_three)
    LinearLayout ll_three;
    @BindView(R.id.ll_four)
    LinearLayout ll_four;

    @BindView(R.id.iv_one)
    ImageView iv_one;
    @BindView(R.id.iv_two)
    ImageView iv_two;
    @BindView(R.id.iv_three)
    ImageView iv_three;
    @BindView(R.id.iv_four)
    ImageView iv_four;

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_three)
    TextView tv_three;
    @BindView(R.id.tv_four)
    TextView tv_four;

    @BindView(R.id.frame_layout)
    FrameLayout frame_layout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBackPress() {
        out();
    }

    /**
     * started
     */
    @Override
    protected void init() {
        imageViewList.add(iv_one);
        imageViewList.add(iv_two);
        imageViewList.add(iv_three);
        imageViewList.add(iv_four);


        for (ImageView iv : imageViewList) {
            iv.setColorFilter(getResources().getColor(R.color.c666666), PorterDuff.Mode.SRC_ATOP);
        }

        textViewList.add(tv_one);
        textViewList.add(tv_two);
        textViewList.add(tv_three);
        textViewList.add(tv_four);

        ll_one.setOnClickListener(this);
        ll_two.setOnClickListener(this);
        ll_three.setOnClickListener(this);
        ll_four.setOnClickListener(this);

        fragmentOne = FragmentOne.newInstance();
        fragmentTwo = FragmentTwo.newInstance();
        fragmentThree = FragmentThree.newInstance();
        fragmentFour = FragmentFour.newInstance();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
        fragmentList.add(fragmentFour);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragmentList) {
            fragmentTransaction.add(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();

        /**
         * 默认选第一个
         */
        select(0);
    }

    @Override
    protected void initInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void showLoginSuccess(LoginBean loginBean) {
    }

    @Override
    protected void registRxBus() {
        mObservable = RxBus.getInstance().register(RxBusConstants.TEST_TAG);
        mDisposable = mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
            }
        });
    }

    @Override
    protected String getRxTag() {
        return RxBusConstants.TEST_TAG;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_one:
                select(0);
                break;
            case R.id.ll_two:
                select(1);
                break;
            case R.id.ll_three:
                select(2);
                break;
            case R.id.ll_four:
                select(3);
                break;
            default:
                break;
        }
    }

    /**
     * 选中事件
     *
     * @param index
     */
    public void select(int index) {
        for (ImageView iv : imageViewList) {
            iv.setColorFilter(getResources().getColor(R.color.c666666), PorterDuff.Mode.SRC_ATOP);
        }
        for (TextView tv : textViewList) {
            tv.setTextColor(getResources().getColor(R.color.c666666));
        }
        imageViewList.get(index).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        textViewList.get(index).setTextColor(getResources().getColor(R.color.colorPrimary));
        showFragment(index);
    }

    /**
     * 显示fragment
     *
     * @param index
     */
    public void showFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment baseFragment : fragmentList) {
            fragmentTransaction.hide(baseFragment);
        }
        fragmentTransaction.show(fragmentList.get(index));
        fragmentTransaction.commit();
    }
}
