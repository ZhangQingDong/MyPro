package com.example.zqd.myproject.ui.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.adapter.listadapter.NewsAdapter;
import com.example.zqd.myproject.base.BaseFragment;
import com.example.zqd.myproject.constancs.Constants;
import com.example.zqd.myproject.contract.NewsListFragmentContract;
import com.example.zqd.myproject.dagger2.component.FragmentComponent;
import com.example.zqd.myproject.model.bean.NewsListBean;
import com.example.zqd.myproject.presenter.activity.NewsListFragmentPresenter;
import com.example.zqd.myproject.ui.statelayout.StatefulLayout;
import com.example.zqd.myproject.utils.ToastUtil;

import java.util.List;


public class NewsListFragment extends BaseFragment<NewsListFragmentPresenter> implements NewsListFragmentContract.View {

    private StatefulLayout status_layout;
    private RecyclerView rv_fragment_list;

    private NewsAdapter newsAdapter;
    private boolean isFirstIn = true;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_simple_list;
    }

    @Override
    protected void init() {
        rv_fragment_list = mRootView.findViewById(R.id.rv_fragment_list);
        status_layout = mRootView.findViewById(R.id.status_layout);
        rv_fragment_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
    }

    @Override
    protected void initInject(FragmentComponent component) {
        component.inject(this);
    }

    /**
     * 由adapter调用并开始访问网络
     */
    public void getData() {
        ToastUtil.showShort(getArguments().getString("id") + "");
        presenter.getData(getArguments().getString("id"), Constants.ACTION_DEFAULT, 1);
    }

    @Override
    public void getErrorData() {
        super.getErrorData();
        status_layout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void getEmptyData() {
        super.getEmptyData();
        status_layout.showEmpty(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void showData(List<NewsListBean.ItemBean> itemBeanList) {
        Log.i("zqd", "执行了");
        newsAdapter = new NewsAdapter(itemBeanList, getActivity());
        rv_fragment_list.setAdapter(newsAdapter);
    }
}
