package com.example.pxh.library;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaohong.peng on 2017/2/17.
 * 该adapter采用的是装饰者模式，对PXHAdapterForRecyclerView在进行一层的封装
 */

public class PXHHeaderAndFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private static final int BASE_ITEM_TYPE_HEADER = 6688;
    private static final int BASE_ITEM_TYPE_FOOTER = 8866;
    /**
     * 创建两个放置头和尾的容器
     */
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();
    /**
     * 这个是被装饰的adapter
     */
    private RecyclerView.Adapter mInnerAdapter;
    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }
    public PXHHeaderAndFooterAdapter(RecyclerView.Adapter innerAdapter) {
        mInnerAdapter = innerAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new RecyclerView.ViewHolder(mHeaderViews.get(viewType)) {
            };
        } else if (mFooterViews.get(viewType) != null) {
            return new RecyclerView.ViewHolder(mFooterViews.get(viewType)) {
            };
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position)) {
            return;
        }
        if (isFooterView(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }
    /**
     * 这个是负责给gridview添加头和尾的，因为gridview是有单元格的，如果直接的添加，会导致添加的只是某个单元格的头，解决的方法，就 是跨单元格
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (mHeaderViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    } else if (mFooterViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position - getHeadersCount());
                    }
                    return 1;
                }
            });
        }
    }
    /**
     * 列表项出现到可视界面的时候调用
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderView(position) || isFooterView(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }
    /**
     * 添加头view
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加尾view
     * @param view
     */
    public void addFooterView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    /**
     * 判断是否是头view
     * @param position
     * @return
     */
    private boolean isHeaderView(int position) {
        return position < getHeadersCount();
    }

    /**
     * 判断是否是尾view
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    /**
     * 获取头的个数
     * @return
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * 获取尾的个数
     * @return
     */
    public int getFootersCount() {
        return mFooterViews.size();
    }

    /**
     * 获取被装饰着内容的个数
     * @return
     */
    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

}
