package com.example.pxh.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pxh.library.BaseItemsAbslistview.PXHItemView;
import com.example.pxh.library.BaseItemsAbslistview.PXHItemViewManager;

import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public  class PXHMultiItemTypeAdapter<T> extends BaseAdapter{
    private Context mContext;
    private List<T> mDatas;
    /**
     * 多类型的管理
     */
    private final PXHItemViewManager pxhItemViewManager;

    public PXHMultiItemTypeAdapter(Context context, List<T> datas) {
        this.mContext=context;
        this.mDatas=datas;
        pxhItemViewManager = new PXHItemViewManager();
    }

    /**
     * 添加一个类型
     * @param itemViewDelegate
     * @return
     */
    public PXHMultiItemTypeAdapter addItemViewDelegate(PXHItemView<T> itemViewDelegate) {
        pxhItemViewManager.addDelegate(itemViewDelegate);
        return this;
    }

    /**
     * 判断是否有类型
     * @return
     */
    private boolean useItemViewDelegateManager() {
        return pxhItemViewManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = pxhItemViewManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager())
            return pxhItemViewManager.getItemViewDelegateCount();
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        PXHItemView itemViewDelegate = pxhItemViewManager.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        PXHViewHolderForAbsListView viewHolder = null ;
        viewHolder=PXHViewHolderForAbsListView.get(mContext,layoutId,position,view,viewGroup);

        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }
    protected void convert(PXHViewHolderForAbsListView viewHolder, T item, int position) {
        pxhItemViewManager.convert(viewHolder, item, position);
    }

    @Override
    public int getCount() {
        if(mDatas!=null){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
