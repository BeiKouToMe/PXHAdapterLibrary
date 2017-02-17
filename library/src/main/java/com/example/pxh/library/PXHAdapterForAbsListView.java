package com.example.pxh.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 *
 * listview 和gridview的adapter
 */

public abstract class PXHAdapterForAbsListView<T> extends BaseAdapter {
    private Context mContext;
    /**
     * 数据源
     */
    private List<T> mDatas;
    /**
     * 布局的id
     */
    private int mDefaultLayoutId;

    /**
     * 这个是针对于一种item的实现
     * @param context
     * @param data
     * @param defaultLayouId
     */
    public PXHAdapterForAbsListView(Context context, List<T> data, int defaultLayouId) {
        this.mContext = context;
        this.mDatas = data;
        this.mDefaultLayoutId = defaultLayouId;
    }
    @Override
    public int getCount() {
        if (mDatas != null) {
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
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
         //得到item对应的viewholder
        PXHViewHolderForAbsListView viewHolder = getViewHolder(position, view, viewGroup);
         //对item进行数据设置
        convert(viewHolder, getItem(position), position);

        return viewHolder.getConvertView();
    }

    /**
     * 该方法交给开发者去实现
     * @param viewHolder
     * @param item
     * @param position
     */
    public abstract void convert(PXHViewHolderForAbsListView viewHolder, T item, int position) ;

    private PXHViewHolderForAbsListView getViewHolder(int position, View view, ViewGroup viewGroup) {
        return PXHViewHolderForAbsListView.get(mContext, mDefaultLayoutId, position, view, viewGroup);
    }
    //********************下边的方法主要是对数据的处理**************
    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mDatas;
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mDatas.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mDatas.addAll(mDatas.size(), data);
            notifyDataSetChanged();
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
        notifyDataSetChanged();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        mDatas.remove(model);
        notifyDataSetChanged();
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        mDatas.add(position, model);
        notifyDataSetChanged();
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
     * 替换指定索引的数据条目
     */
    public void setItem(int location, T newModel) {
        mDatas.set(location, newModel);
        notifyDataSetChanged();
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mDatas.indexOf(oldModel), newModel);
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        mDatas.add(toPosition, mDatas.remove(fromPosition));
        notifyDataSetChanged();
    }

    /**
     * @return 获取第一个数据模型
     */
    public
    @Nullable
    T getFirstItem() {
        return getCount() > 0 ? getItem(0) : null;
    }

    /**
     * @return 获取最后一个数据模型
     */
    public
    @Nullable
    T getLastItem() {
        return getCount() > 0 ? getItem(getCount() - 1) : null;
    }

}
