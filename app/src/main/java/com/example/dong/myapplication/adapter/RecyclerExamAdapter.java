package com.example.dong.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.myapplication.R;

import java.util.List;

/**
 * Created by DONG on 28-Dec-17.
 */

public class RecyclerExamAdapter extends RecyclerView.Adapter<RecyclerExamAdapter.RecyclerViewHolder> {

    private List<String> list;
    private Context context;

    public RecyclerExamAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView btnExam;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            btnExam = (TextView) itemView.findViewById(R.id.btnExam);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"adff",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerExamAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.custom_exam_layout, parent, false);
        return new RecyclerExamAdapter.RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerExamAdapter.RecyclerViewHolder holder, int position) {
        holder.btnExam.setText("Đề " + list.get(position).toString());
    }
}