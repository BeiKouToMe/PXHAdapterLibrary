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
import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class RecyclerViewHeanAndFootItemsActivity extends AppCompatActivity {
    private RecyclerView rl_no;
    private List<User> data=new ArrayList<>();
    private PXHAdapterForRecyclerView adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_norecyclerview);
        initData();
        initViwe();
    }

    /**
     * 第一种类型
     */
    private int one=R.layout.item_lv_no;
    /**
     * 第二中类型
     */
    private int two=R.layout.item_lv_no;
    private void initViwe() {
        rl_no = (RecyclerView)findViewById(R.id.rl_no);
        rl_no.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PXHAdapterForRecyclerView<User>(this,data) {
            @Override
            public void convert(PXHViewHolderForRecyclerView helper, User item, int position) {
                if(item.getType().equals("one")){
                    helper.setText(R.id.tv_no,item.getMessage());
                    helper.setBackgroundColor(R.id.tv_no, R.color.colorPrimaryDark);
                }else if(item.getType().equals("two")){
                    helper.setText(R.id.tv_no,item.getMessage());
                    helper.setBackgroundColor(R.id.tv_no, R.color.colorAccent);
                }
            }

            @Override
            public int getItemViewType(int position) {
                if(data!=null&&data.size()>0){
                    if(data.get(position).getType().equals("one")){
                        return one;
                    }
                    if(data.get(position).getType().equals("two")){
                        return two;
                    }
                }
                return super.getItemViewType(position);
            }
        };
        rl_no.setAdapter(adapter.getHeaderAndFooterAdapter());
        addHeadAndFoot();
    }
    private void initData() {
        for (int i = 0; i <100 ; i++) {
            if(i%3==0){
                data.add(new User("one","第"+i+"条数据"));
            }else{
                data.add(new User("two","第"+i+"条数据"));
            }

        }
    }
    private void addHeadAndFoot() {
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tv.setText("heaer");
        adapter.addHeaderView(tv);
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv1.setGravity(Gravity.CENTER);
        tv1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tv1.setText("end");
        adapter.addFooterView(tv1);

    }
    /**
     * 跳转到该activity
     * @param mainActivity
     */
    public static void obtaionNoRecyclerViewHeadAndFootItemsActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, RecyclerViewHeanAndFootItemsActivity.class);
        mainActivity.startActivity(intent);
    }
}
