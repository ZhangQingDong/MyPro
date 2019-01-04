package com.example.zqd.myproject.adapter.listadapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zqd.myproject.R;
import com.example.zqd.myproject.model.bean.NewsListBean;

import java.util.List;

public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsListBean.ItemBean, BaseViewHolder> {

    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewsAdapter(List<NewsListBean.ItemBean> data, Context context) {
        super(data);
        this.context = context;
        addItemType(NewsListBean.ItemBean.TYPE_DOC, R.layout.item_news);
        addItemType(NewsListBean.ItemBean.TYPE_PHVIDEO, R.layout.item_video);
        addItemType(-1, R.layout.item_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListBean.ItemBean item) {
        switch (helper.getItemViewType()) {
            case NewsListBean.ItemBean.TYPE_DOC:
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_from, item.getSource() + " 评论" + item.getComments());
                Glide.with(context).load(item.getThumbnail()).into((ImageView) helper.getConvertView().findViewById(R.id.iv_photo));
                break;
            case NewsListBean.ItemBean.TYPE_PHVIDEO:
                break;
            default:
                break;
        }
    }
}
