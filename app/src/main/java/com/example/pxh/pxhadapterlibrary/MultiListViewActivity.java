package com.example.pxh.pxhadapterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.pxh.library.PXHMultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class MultiListViewActivity extends AppCompatActivity {
    private List<User> data=new ArrayList<>();
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
        PXHMultiItemTypeAdapter<User> userPXHMultiItemTypeAdapter = new PXHMultiItemTypeAdapter<>(this, data);
        userPXHMultiItemTypeAdapter.addItemViewDelegate(new OneItem());
        userPXHMultiItemTypeAdapter.addItemViewDelegate(new TwoItem());
        lv_no.setAdapter(userPXHMultiItemTypeAdapter);
    }
    /**
     * 跳转到该activity
     * @param mainActivity
     */
    public static void obtaionMultiListViewActivity(MainActivity mainActivity){
        Intent intent = new Intent(mainActivity, MultiListViewActivity.class);
        mainActivity.startActivity(intent);
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
}
