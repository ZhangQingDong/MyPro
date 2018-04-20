package com.example.zqd.myproject.ui.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.adapter.listadapter.FragmentOneAdapter;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.dagger2.component.FragmentComponent;
import com.example.zqd.myproject.ui.statelayout.StatefulLayout;

import java.util.ArrayList;

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

    @BindView(R.id.rv_fragment_list)
    RecyclerView rvFragmentList;
    @BindView(R.id.status_layout)
    StatefulLayout statusLayout;

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
        statusLayout.showContent();
        rvFragmentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(i + "");
        }
        rvFragmentList.setAdapter(new FragmentOneAdapter(R.layout.item_text, strings));
    }

    @Override
    protected void initInject(FragmentComponent component) {
    }

}
