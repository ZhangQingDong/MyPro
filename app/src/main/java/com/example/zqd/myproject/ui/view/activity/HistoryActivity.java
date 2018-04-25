package com.example.zqd.myproject.ui.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zqd.myproject.R;
import com.example.zqd.myproject.adapter.listadapter.HistoryAdapter;
import com.example.zqd.myproject.base.BaseActivity;
import com.example.zqd.myproject.constancs.RxBusConstants;
import com.example.zqd.myproject.contract.HistoryActivityContract;
import com.example.zqd.myproject.dagger2.component.ActivityComponent;
import com.example.zqd.myproject.model.bean.HistoryBean;
import com.example.zqd.myproject.presenter.activity.HistoryActivityPresenter;
import com.example.zqd.myproject.ui.customview.CommonToolBar;
import com.example.zqd.myproject.ui.statelayout.StatefulLayout;
import com.example.zqd.myproject.utils.RxBus;

import java.util.List;

import butterknife.BindView;

/**
 * <p>Title: com.example.zqd.myproject.ui.view.activity</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/24 10:55
 */
public class HistoryActivity extends BaseActivity<HistoryActivityPresenter> implements HistoryActivityContract.View {

    @BindView(R.id.toolbar)
    CommonToolBar toolbar;
    @BindView(R.id.rv_fragment_list)
    RecyclerView rvFragmentList;
    @BindView(R.id.status_layout)
    StatefulLayout statusLayout;

    private HistoryAdapter adapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void onBackPress() {
        out();
    }

    @Override
    protected void init() {
        toolbar.getRl_left().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });
        getData();
        RxBus.getInstance().post(RxBusConstants.TEST_TAG, "");
    }

    @Override
    protected void initInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void getEmptyData() {
        super.getEmptyData();
        statusLayout.showEmpty(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    @Override
    public void getErrorData() {
        super.getErrorData();
        statusLayout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    @Override
    public void showData(List<HistoryBean.ShowapiResBodyEntity.ListEntity> lists) {
        statusLayout.showContent();
        if (lists.isEmpty()) {
            getEmptyData();
        } else {
            if (adapter == null) {
                adapter = new HistoryAdapter(this, R.layout.item_history, lists);
                rvFragmentList.setLayoutManager(new LinearLayoutManager(this));
                rvFragmentList.setAdapter(adapter);
            } else {
                adapter.getData().clear();
                adapter.getData().addAll(lists);
                adapter.notifyDataSetChanged();
            }
        }
    }

    void getData() {
        presenter.getData();
    }

}
