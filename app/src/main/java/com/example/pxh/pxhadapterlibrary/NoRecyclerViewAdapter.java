package com.example.pxh.pxhadapterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pxh.library.PXHAdapterForRecyclerView;
import com.example.pxh.library.PXHViewHolderForRecyclerView;

import java.util.ArrayList;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class NoRecyclerViewAdapter extends AppCompatActivity {
    private RecyclerView rl_no;
    private ArrayList<String> mDatas=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_norecyclerview);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i <100 ; i++) {
            mDatas.add("第"+i+"条数据");
        }
    }
    /**
     * 跳转到该activity
     * @param mainActivity
     */
    public static void obtaionNoRecyclerViewActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, NoRecyclerViewAdapter.class);
        mainActivity.startActivity(intent);
    }
    private void initView() {
        rl_no = (RecyclerView)findViewById(R.id.rl_no);
        rl_no.setLayoutManager(new LinearLayoutManager(this));
        rl_no.setAdapter(new PXHAdapterForRecyclerView<String>(this,R.layout.item_lv_no,mDatas) {

            @Override
            public void convert(PXHViewHolderForRecyclerView helper, String item, int position) {
                helper.setText(R.id.tv_no,item);
            }
        });
    }
}
