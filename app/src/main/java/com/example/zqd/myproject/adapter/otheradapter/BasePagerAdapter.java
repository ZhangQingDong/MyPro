package com.example.zqd.myproject.adapter.otheradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zqd.myproject.base.BaseFragment;

import java.util.List;

/**
 * <p>Title: com.example.zqd.myproject.adapter.otheradapter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 15:16
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titleList;
    private List<BaseFragment> fragmentList;

    public BasePagerAdapter(FragmentManager fm, List<String> titleList, List<BaseFragment> fragmentList) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
