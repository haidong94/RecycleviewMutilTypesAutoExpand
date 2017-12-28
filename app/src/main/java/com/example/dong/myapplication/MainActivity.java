package com.example.dong.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dong.myapplication.adapter.MyAdapter;
import com.example.dong.myapplication.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setData();


    }

    private void setData() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0) {
                Item item = new Item("this is item" + (i + 1), "this is child item" + (i + 1), true);
                items.add(item);
            }else {
                Item item = new Item("this is item" + (i + 1),"đdđ", false);
                items.add(item);
            }

        }

        MyAdapter myAdapter = new MyAdapter(items);
        recyclerView.setAdapter(myAdapter);
    }


}
