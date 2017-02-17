package com.example.pxh.pxhadapterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.pxh.library.PXHAdapterForAbsListView;
import com.example.pxh.library.PXHViewHolderForAbsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class NoGridViewActivity extends AppCompatActivity {
    private List<String> mDatas=new ArrayList<>();
    private GridView gv_no;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nogridview);
        initData();
        initView();

    }

    private void initView() {
        gv_no = (GridView) findViewById(R.id.gv_no);
        gv_no.setAdapter(new PXHAdapterForAbsListView<String>(this,mDatas,R.layout.item_lv_no) {
            @Override
            public void convert(PXHViewHolderForAbsListView viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv_no,mDatas.get(position));
            }
        });
    }


    /**
     * 跳转到该activity
     * @param mainActivity
     */
    public static void obtaionNoGridViewActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, NoGridViewActivity.class);
        mainActivity.startActivity(intent);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mDatas.add("第一条数据"+i);
        }
    }
}
