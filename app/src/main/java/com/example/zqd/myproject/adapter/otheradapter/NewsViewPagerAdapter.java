package com.example.zqd.myproject.adapter.otheradapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.ui.view.fragment.NewsListFragment;

import java.util.List;

public class NewsViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> titleList;
    private List<BaseFragment> fragmentList;

    public NewsViewPagerAdapter(FragmentManager fm, List<String> titleList, List<BaseFragment> fragmentList) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void initFragmentData(int index) {
        ((NewsListFragment) fragmentList.get(index)).getData();
    }
}
