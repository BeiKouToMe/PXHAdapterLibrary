package com.example.pxh.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by xiaohong.peng on 2017/2/17.
 * listview和gridview的viewholer
 */

public class PXHViewHolderForAbsListView {
    private Context mContext;
    private SparseArray<View> mViews;
    private  View mConvertView;

    public PXHViewHolderForAbsListView(Context context, int defaultLayoutId, ViewGroup parent) {
        this.mContext=context;
        mViews=new SparseArray<>();
        mConvertView = View.inflate(context, defaultLayoutId, null);
        mConvertView.setTag(this);
    }

    /**
     * 该方法是得到ViewHolder
     * @param context
     * @param defaultLayoutId
     * @param position
     * @param convertView
     * @param viewGroup
     * @return
     */
    public static PXHViewHolderForAbsListView get(Context context,int defaultLayoutId,int position,View convertView,ViewGroup viewGroup){
        PXHViewHolderForAbsListView viewHolder;
        if(convertView==null){
            viewHolder=new PXHViewHolderForAbsListView(context,defaultLayoutId,viewGroup);
        }else{
            viewHolder = (PXHViewHolderForAbsListView) convertView.getTag();
        }
        return viewHolder;
    }

    /**
     * 根据xml布局的id 的到view 并且对该view进行保存
     * @param viewId
     * @param <T>
     * @return
     */
   public <T extends View> T getView(int viewId){
       //优先从SparseArray里进行取出
       View view = mViews.get(viewId);
       if(view==null){
           //说明还没有获取
           view=mConvertView.findViewById(viewId);
           mViews.put(viewId,view);
       }
       return (T) view;
   }

    /**
     * 获取到当前的item对应的view
     * @return
     */
    public View getConvertView(){
        return mConvertView;
    }
    //************************布局中可能出现的view************假如不存在，那就由开发者同过getview方法进行获取设置***********
    /**
     * 设置TextView文字，并返回this
     */
    public PXHViewHolderForAbsListView setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置TextView的文字颜色，并返回this
     */
    public PXHViewHolderForAbsListView setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForAbsListView setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForAbsListView setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public PXHViewHolderForAbsListView setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置背景颜色，并返回this
     */
    public PXHViewHolderForAbsListView setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置背景资源，并返回this
     */
    public PXHViewHolderForAbsListView setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置显隐，并返回this
     */
    public PXHViewHolderForAbsListView setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置是否可用，并返回this
     */
    public PXHViewHolderForAbsListView setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置是否可获取焦点，并返回this
     */
    public PXHViewHolderForAbsListView setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }
}
