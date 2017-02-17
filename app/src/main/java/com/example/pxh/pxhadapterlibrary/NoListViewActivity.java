package com.example.pxh.pxhadapterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.pxh.library.PXHAdapterForAbsListView;
import com.example.pxh.library.PXHViewHolderForAbsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class NoListViewActivity extends AppCompatActivity {
    private List<String> mDatas=new ArrayList<>();
    private ListView lv_no;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nolistview);
        initData();
        initView();

    }

    private void initView() {
        lv_no = (ListView)findViewById(R.id.lv_no);
        lv_no.setAdapter(new PXHAdapterForAbsListView<String>(this,mDatas,R.layout.item_lv_no) {
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
    public static void obtaionNoListViewActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, NoListViewActivity.class);
        mainActivity.startActivity(intent);
    }

    private void initData() {

        for (int i = 0; i < 100; i++) {
            mDatas.add("第一条数据"+i);
        }
    }
}
