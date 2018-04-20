package com.example.zqd.myproject.ui.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.adapter.otheradapter.BasePagerAdapter;
import com.example.zqd.myproject.base.BaseActivity;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.ui.view.fragment.FragmentOne;
import com.example.zqd.myproject.ui.view.fragment.FragmentThree;
import com.example.zqd.myproject.ui.view.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Title: com.example.zqd.myproject.ui.view.activity</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 14:48
 */
public class TabActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<String> titleLists = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void onBackPress() {
        finish();
    }

    @Override
    protected void init() {
        fragmentList.add(FragmentOne.newInstance());
        fragmentList.add(FragmentTwo.newInstance());
        fragmentList.add(FragmentThree.newInstance());
        titleLists.add("TAB1");
        titleLists.add("TAB2");
        titleLists.add("TAB3");
        viewPager.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), titleLists, fragmentList));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initInject(ActivityComponent component) {
    }

}
