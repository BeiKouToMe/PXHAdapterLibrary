package com.example.pxh.library.BaseItemsAbslistview;

import android.support.v4.util.SparseArrayCompat;

import com.example.pxh.library.PXHViewHolderForAbsListView;


/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class PXHItemViewManager<T> {
    /**
     * 初始化一个类似于map集合的容器,是用来存储多条目
     */
    SparseArrayCompat<PXHItemView<T>> delegates = new SparseArrayCompat();

    /**
     * 获取总共有多少个条目
     * @return
     */
    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    /**
     * 增加一个条目
     * @param delegate
     * @return
     */
    public PXHItemViewManager<T> addDelegate(PXHItemView<T> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    /**
     * 在某个角标插入一个条目
     * @param viewType
     * @param delegate
     * @return
     */
    public PXHItemViewManager<T> addDelegate(int viewType, PXHItemView<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    /**
     * 移除一个条目
     * @param delegate
     * @return
     */
    public PXHItemViewManager<T> removeDelegate(PXHItemView<T> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 根据角标移除
     * @param itemType
     * @return
     */
    public PXHItemViewManager<T> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            PXHItemView<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(PXHViewHolderForAbsListView holder, T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            PXHItemView<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public int getItemViewLayoutId(int viewType)
    {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(PXHItemView itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }

    public PXHItemView getItemViewDelegate(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            PXHItemView<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position))
            {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(T item, int position)
    {
        return getItemViewDelegate(item,position).getItemViewLayoutId();
    }
}
