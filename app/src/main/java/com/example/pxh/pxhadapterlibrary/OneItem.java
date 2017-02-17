package com.example.pxh.pxhadapterlibrary;

import com.example.pxh.library.BaseItemsAbslistview.PXHItemView;
import com.example.pxh.library.PXHViewHolderForAbsListView;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class OneItem implements PXHItemView<User> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_lv_no;
    }

    @Override
    public boolean isForViewType(User item, int position) {
        if(item.getType().equals("one")){
            return true;
        }
        return false;
    }

    @Override
    public void convert(PXHViewHolderForAbsListView holder, User user, int position) {
        holder.setBackgroundColor(R.id.tv_no, R.color.colorPrimary);
        holder.setText(R.id.tv_no,user.getMessage());
    }
}
