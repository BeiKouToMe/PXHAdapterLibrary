package com.example.pxh.pxhadapterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pxh.library.PXHAdapterForRecyclerView;
import com.example.pxh.library.PXHViewHolderForRecyclerView;

import java.util.ArrayList;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class NoRecyclerViewHeadAndFootActivity extends AppCompatActivity {
    private RecyclerView rl_no;
    private ArrayList<String> mDatas=new ArrayList<>();
    private PXHAdapterForRecyclerView<String> inlAdapter;

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

    private void initView() {
        rl_no = (RecyclerView)findViewById(R.id.rl_no);
        rl_no.setLayoutManager(new LinearLayoutManager(this));
        inlAdapter = new PXHAdapterForRecyclerView<String>(this,R.layout.item_lv_no, mDatas) {
            @Override
            public void convert(PXHViewHolderForRecyclerView helper, String item, int position) {
                helper.setText(R.id.tv_no,item);
            }


        };
        rl_no.setAdapter(inlAdapter.getHeaderAndFooterAdapter());
        addHeadAndFoot();

    }

    private void addHeadAndFoot() {
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tv.setText("heaer");
        inlAdapter.addHeaderView(tv);
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv1.setGravity(Gravity.CENTER);
        tv1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tv1.setText("end");
        inlAdapter.addFooterView(tv1);

    }
    /**
     * 跳转到该activity
     * @param mainActivity
     */
    public static void obtaionNoRecyclerViewHeadAndFootActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, NoRecyclerViewHeadAndFootActivity.class);
        mainActivity.startActivity(intent);
    }
}
