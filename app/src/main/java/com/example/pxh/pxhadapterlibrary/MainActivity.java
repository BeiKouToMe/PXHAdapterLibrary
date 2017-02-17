package com.example.pxh.pxhadapterlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_no_listview;
    private Button bt_no_gridview;
    private Button bt_listview;
    private Button bt_recycler;
    private Button bt_recycler_headandfoot;
    private Button bt_recycler_heada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();


    }

    private void initListener() {
        bt_no_listview.setOnClickListener(this);
        bt_no_gridview.setOnClickListener(this);
        bt_listview.setOnClickListener(this);
        bt_recycler.setOnClickListener(this);
        bt_recycler_headandfoot.setOnClickListener(this);
        bt_recycler_heada.setOnClickListener(this);
    }

    private void initView() {
        bt_no_listview = (Button) findViewById(R.id.bt_no_listview);
        bt_no_gridview = (Button) findViewById(R.id.bt_no_gridview);
        bt_listview = (Button)findViewById(R.id.bt_listview);
        bt_recycler = (Button)findViewById(R.id.bt_recycler);
        bt_recycler_headandfoot = (Button)findViewById(R.id.bt_recycler_headandfoot);
        bt_recycler_heada = (Button)findViewById(R.id.bt_recycler_heada);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_no_listview:
                 NoListViewActivity.obtaionNoListViewActivity(this);
                break;
            case R.id.bt_no_gridview:
                 NoGridViewActivity.obtaionNoGridViewActivity(this);
                break;
            case R.id.bt_listview:
                MultiListViewActivity.obtaionMultiListViewActivity(this);
                break;
            case R.id.bt_recycler:
                NoRecyclerViewAdapter.obtaionNoRecyclerViewActivity(this);
                break;
            case R.id.bt_recycler_headandfoot:
                NoRecyclerViewHeadAndFootActivity.obtaionNoRecyclerViewHeadAndFootActivity(this);
                break;
            case R.id.bt_recycler_heada:
                RecyclerViewHeanAndFootItemsActivity.obtaionNoRecyclerViewHeadAndFootItemsActivity(this);
                break;

            default:
                break;
        }
    }
}
