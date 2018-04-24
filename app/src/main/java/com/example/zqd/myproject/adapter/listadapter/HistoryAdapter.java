package com.example.zqd.myproject.adapter.listadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zqd.myproject.R;
import com.example.zqd.myproject.model.bean.HistoryBean;
import com.example.zqd.myproject.utils.ImageUtil;

import java.util.List;

/**
 * <p>Title: com.example.zqd.myproject.adapter.listadapter</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/24 13:16
 */
public class HistoryAdapter extends BaseQuickAdapter<HistoryBean.ShowapiResBodyEntity.ListEntity, BaseViewHolder> {

    private Context context;

    public HistoryAdapter(Context context, int layoutResId, @Nullable List<HistoryBean.ShowapiResBodyEntity.ListEntity> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean.ShowapiResBodyEntity.ListEntity item) {
        helper.setText(R.id.tv_title, item.getTitle());
        ImageUtil.loadImage(context, item.getImg(), (ImageView) helper.getView(R.id.iv_picture));
    }
}
