package com.example.zqd.myproject.ui.view.fragment;

import android.os.Bundle;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.dagger2.component.FragmentComponent;

/**
 * <p>Title: com.example.zqd.myproject.ui.view.fragment</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * @author zhangqingdong
 * @version 4.0
 * @date 2018/4/20 15:01
 */
public class FragmentThree extends BaseFragment {

    public static FragmentThree newInstance() {
        Bundle args = new Bundle();
        FragmentThree fragment = new FragmentThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initInject(FragmentComponent component) {
    }
}
