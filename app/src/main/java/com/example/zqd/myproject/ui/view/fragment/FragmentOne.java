package com.example.zqd.myproject.ui.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.zqd.greendao.TabBeanDao;
import com.example.zqd.myproject.R;
import com.example.zqd.myproject.adapter.otheradapter.NewsViewPagerAdapter;
import com.example.zqd.myproject.base.BaseApplication;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.dagger2.component.FragmentComponent;
import com.example.zqd.myproject.model.bean.TabBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>Title: com.example.zqd.myproject.ui.view.fragment</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 15:01
 */
public class FragmentOne extends BaseFragment {

    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private List<String> titleList = new ArrayList<>();
    private List<BaseFragment> fragmentList = new ArrayList<>();

    private NewsViewPagerAdapter newsViewPagerAdapter;

    public static FragmentOne newInstance() {
        Bundle args = new Bundle();
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void init() {
        initTag();
        initFragment();
    }

    private void initFragment() {
        TabBeanDao tabBeanDao = BaseApplication.getApp().getDaoSession().getTabBeanDao();
        List<TabBean> list = tabBeanDao.queryBuilder().list();
        for (int i = 0; i < list.size(); i++) {
            titleList.add(list.get(i).getName());
            NewsListFragment newsListFragment = NewsListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("id", list.get(i).getId());
            newsListFragment.setArguments(bundle);
            fragmentList.add(newsListFragment);
        }

        newsViewPagerAdapter = new NewsViewPagerAdapter(getChildFragmentManager(), titleList, fragmentList);
        view_pager.setAdapter(newsViewPagerAdapter);
        view_pager.setOffscreenPageLimit(3);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab_layout.setupWithViewPager(view_pager);
    }

    /**
     * 初始化tab数据库
     */
    private void initTag() {
        TabBeanDao tabBeanDao = BaseApplication.getApp().getDaoSession().getTabBeanDao();
        List<TabBean> list = tabBeanDao.queryBuilder().list();
        if (list.isEmpty()) {
            tabBeanDao.insert(new TabBean("SYLB10,SYDT10", "头条", true));
            tabBeanDao.insert(new TabBean("YL53,FOCUSYL53", "娱乐", true));
            tabBeanDao.insert(new TabBean("JS83,FOCUSJS83", "军事", true));
            tabBeanDao.insert(new TabBean("TY43,FOCUSTY43,TYLIVE,TYTOPIC", "体育", true));
            tabBeanDao.insert(new TabBean("CJ33,FOCUSCJ33,HNCJ33", "财经", true));
            tabBeanDao.insert(new TabBean("KJ123,FOCUSKJ123", "科技", true));
            tabBeanDao.insert(new TabBean("LS153,FOCUSLS153", "历史", true));
            tabBeanDao.insert(new TabBean("TW73", "台湾", true));
            tabBeanDao.insert(new TabBean("QC45,FOCUSQC45", "汽车", true));
            tabBeanDao.insert(new TabBean("SH133,FOCUSSH133", "社会", true));
            tabBeanDao.insert(new TabBean("SS78,FOCUSSS78", "时尚", true));
            tabBeanDao.insert(new TabBean("GXPD,FOCUSGXPD", "国学", true));
            tabBeanDao.insert(new TabBean("WH25,FOCUSWH25", "文化", true));
            tabBeanDao.insert(new TabBean("XZ09,FOCUSXZ09", "星座", true));
            tabBeanDao.insert(new TabBean("DS57,FOCUSDS57", "读书", true));
            tabBeanDao.insert(new TabBean("YX11,FOCUSYX11", "游戏", true));
            tabBeanDao.insert(new TabBean("DYPD", "电影", true));
            tabBeanDao.insert(new TabBean("GJPD", "国际", true));
        }
    }

    @Override
    protected void initInject(FragmentComponent component) {
    }

}
