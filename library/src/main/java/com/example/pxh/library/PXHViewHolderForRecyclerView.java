package com.example.pxh.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class PXHViewHolderForRecyclerView  extends RecyclerView.ViewHolder{
    private Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;
    public PXHViewHolderForRecyclerView(View itemView, Context context) {
        super(itemView);
        this.mContext=context;
        mViews=new SparseArray<>();
        this.mConvertView=itemView;

    }
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view = mConvertView.findViewById(viewId);
        }
        return (T) view;
    }
    public View getmConvertView(){
        return mConvertView;
    }
  /*================== 一切有可能的操作控件的方法 begin ==================*/

    /**
     * 设置TextView文字，并返回this
     */
    public PXHViewHolderForRecyclerView setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置TextView的文字颜色，并返回this
     */
    public PXHViewHolderForRecyclerView setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForRecyclerView setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForRecyclerView setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForRecyclerView setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置背景颜色，并返回this
     */
    public PXHViewHolderForRecyclerView setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置背景资源，并返回this
     */
    public PXHViewHolderForRecyclerView setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置显隐，并返回this
     */
    public PXHViewHolderForRecyclerView setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置是否可用，并返回this
     */
    public PXHViewHolderForRecyclerView setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置是否可获取焦点，并返回this
     */
    public PXHViewHolderForRecyclerView setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }

    /*================== 一切有可能操作控件的方法 end ==================*/
}
