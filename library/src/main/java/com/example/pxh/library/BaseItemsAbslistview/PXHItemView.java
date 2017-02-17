package com.example.pxh.library.BaseItemsAbslistview;

import com.example.pxh.library.PXHViewHolderForAbsListView;

/**
 * Created by xiaohong.peng on 2017/2/17.
 * 多条目中的其中一个单条目
 */

public interface PXHItemView<T> {

    public abstract int getItemViewLayoutId();

    /**
     * 这个主要是区分两方，例如即时通讯里聊天室，接收方和发送方，要是只是处理普通的多类型，一般都返回true
     * @param item
     * @param position
     * @return
     */
    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(PXHViewHolderForAbsListView holder, T t, int position);
}
