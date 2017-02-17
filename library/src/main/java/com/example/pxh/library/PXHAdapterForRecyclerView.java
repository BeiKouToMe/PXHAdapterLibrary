package com.example.pxh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public abstract class PXHAdapterForRecyclerView<T> extends RecyclerView.Adapter<PXHViewHolderForRecyclerView>{
    private Context mContext;
    private List<T> mDatas;
    private int mDefaultLayoutId;
    private PXHHeaderAndFooterAdapter mHeaderAndFooterAdapter;
    /**
     * 这个是使用多种类型的时候
     * @param context
     * @param mDatas
     */
    public PXHAdapterForRecyclerView(Context context,List<T> mDatas) {
        this.mContext=context;
        this.mDatas=mDatas;
    }
    /**
     * 当使用一种itemType时，最好使用这种构造方法
     */
    public PXHAdapterForRecyclerView(Context context, int defaultLayoutId, List<T> data) {
        this(context,data);
        mDefaultLayoutId = defaultLayoutId;
    }

    @Override
    public PXHViewHolderForRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PXHViewHolderForRecyclerView(View.inflate(mContext,viewType,null),mContext);
    }

    @Override
    public void onBindViewHolder(PXHViewHolderForRecyclerView holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(mDatas!=null){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDefaultLayoutId == 0) {
        throw new RuntimeException("请在 " + this.getClass().getSimpleName() + " 中没有布局文件，请写入布局文件 ");
    }
        return mDefaultLayoutId;
    }

    public abstract void convert(PXHViewHolderForRecyclerView helper, T item, int position);
 /*================== 数据操作相关 begin ==================*/

    /**
     * 获取指定索引位置的数据模型
     */
    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mDatas;
    }

    /**
     * 数据局部刷新
     */
    public final void notifyItemRangeInsertedWrapper(int positionStart, int itemCount) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            mHeaderAndFooterAdapter.notifyItemRangeInserted(mHeaderAndFooterAdapter.getHeadersCount() + positionStart, itemCount);
        }
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mDatas.addAll(0, data);
            notifyItemRangeInsertedWrapper(0, data.size());
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mDatas.addAll(mDatas.size(), data);
            notifyItemRangeInsertedWrapper(mDatas.size(), data.size());
        }
    }

    /**
     * 数据全局刷新
     */
    public final void notifyDataSetChangedWrapper() {
        if (mHeaderAndFooterAdapter == null) {
            notifyDataSetChanged();
        } else {
            mHeaderAndFooterAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     */
    public void setData(List<T> data) {
        if (data != null) {
            if (mDatas == null) {
                mDatas = data;
            } else {
                mDatas.clear();
                mDatas.addAll(data);
            }
        } else {
            mDatas.clear();
        }
        notifyDataSetChangedWrapper();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mDatas.clear();
        notifyDataSetChangedWrapper();
    }

    /**
     * 数据移除刷新
     */
    public final void notifyItemRemoveWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemRemoved(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemRemoved(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoveWrapper(position);
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        removeItem(mDatas.indexOf(model));
    }

    /**
     * 数据添加刷新
     */
    public final void notifyItemInsertedWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemInserted(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemInserted(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        mDatas.add(position, model);
        notifyItemInsertedWrapper(position);
    }

    /**
     * 在集合头部添加数据条目
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     */
    public void addLastItem(T model) {
        addItem(mDatas.size(), model);
    }

    /**
     * 数据变化刷新
     */
    public final void notifyItemChangedWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemChanged(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemChanged(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 替换指定索引的数据条目
     */
    public void setItem(int position, T newModel) {
        mDatas.set(position, newModel);
        notifyItemChangedWrapper(position);
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mDatas.indexOf(oldModel), newModel);
    }

    /**
     * 数据移动刷新
     */
    public final void notifyItemMoveWrapper(int fromPosition, int toPosition) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemMoved(fromPosition, toPosition);
        } else {
            mHeaderAndFooterAdapter.notifyItemMoved(mHeaderAndFooterAdapter.getHeadersCount() + fromPosition, mHeaderAndFooterAdapter.getHeadersCount() + toPosition);
        }
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        notifyItemChangedWrapper(fromPosition);
        notifyItemChangedWrapper(toPosition);

        //要先执行上面的notifyItemChanged，然后再执行下面的moveItem事件

        mDatas.add(toPosition, mDatas.remove(fromPosition));
        notifyItemMoveWrapper(fromPosition, toPosition);
    }

    /**
     * 获取第一个数据模型
     */
    public T getFirstItem() {
        return getItemCount() > 0 ? getItem(0) : null;
    }

    /**
     * 得到最后一个数据模型
     */
    public T getLastItem() {
        return getItemCount() > 0 ? getItem(getItemCount() - 1) : null;
    }

    /*================== 数据操作相关 end ==================*/
      /*================== 头部、尾部部相关 begin ==================*/

    public void addHeaderView(View headerView) {
        getHeaderAndFooterAdapter().addHeaderView(headerView);
    }

    public void addFooterView(View footerView) {
        getHeaderAndFooterAdapter().addFooterView(footerView);
    }

    public int getHeadersCount() {
        return mHeaderAndFooterAdapter == null ? 0 : mHeaderAndFooterAdapter.getHeadersCount();
    }

    public int getFootersCount() {
        return mHeaderAndFooterAdapter == null ? 0 : mHeaderAndFooterAdapter.getFootersCount();
    }

    /**
     * 判断是否是头部或尾部
     */
    public boolean isHeaderOrFooter(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() < getHeadersCount() || viewHolder.getAdapterPosition() >= getHeadersCount() + getItemCount();
    }

    public PXHHeaderAndFooterAdapter getHeaderAndFooterAdapter() {
        if (mHeaderAndFooterAdapter == null) {
            synchronized (PXHHeaderAndFooterAdapter.class) {
                if (mHeaderAndFooterAdapter == null) {
                    mHeaderAndFooterAdapter = new PXHHeaderAndFooterAdapter(this);
                }
            }
        }
        return mHeaderAndFooterAdapter;
    }

    /*================== 头部、尾部相关 end ==================*/
}
