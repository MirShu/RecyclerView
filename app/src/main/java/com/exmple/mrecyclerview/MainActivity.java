package com.exmple.mrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.exmple.mrecyclerview.recycler.RecyclerAdapter;
import com.exmple.mrecyclerview.recycler.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rl_newscontent_content_view);
        this.bindRecycleView();
    }


    private void bindRecycleView() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }

        //1. 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //2. 网格布局
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        //3. 瀑布流
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.adapter = new RecyclerAdapter<String>(this, list,
                R.layout.item_address) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                TextView tv_item = helper.getView(R.id.tv_item);
                tv_item.setText("item" + position);
            }
        };
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new RecyclerItemDecoration(this));
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Toast.makeText(MainActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}