package com.example.dong.myapplication.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.myapplication.R;
import com.example.dong.myapplication.model.Item;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONG on 18-Dec-17.
 */

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder {

    public TextView tvChildOut;
    public MyViewHolderWithoutChild(View itemView) {
        super(itemView);
        tvChildOut=itemView.findViewById(R.id.tvChildOut);
    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder {

    View image;
    TextView tvChild,tvsubChild;
    ExpandableLinearLayout expandlayout;
    RecyclerView recyclerView;
    public MyViewHolderWithChild(View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.image);
        tvChild=itemView.findViewById(R.id.tvChild);
        tvsubChild=itemView.findViewById(R.id.tvsubChild);
        expandlayout=itemView.findViewById(R.id.expandlayout);
        recyclerView=itemView.findViewById(R.id.recyclerView);

    }
}
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Item> items;
    Context context;
    SparseBooleanArray expandState=new SparseBooleanArray();

    public MyAdapter(List<Item> items) {
        this.items = items;
        for (int i=0;i<items.size();i++){
            expandState.append(i,false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).isExpandable()){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        if(viewType==0){
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.layout_without_child,parent,false);
            return new MyViewHolderWithoutChild(view);
        }
        else {
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.layout_with_child,parent,false);
            return new MyViewHolderWithChild(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()){
            case 0:{
                MyViewHolderWithoutChild viewholder= (MyViewHolderWithoutChild) holder;
                Item item=items.get(position);
                viewholder.setIsRecyclable(false);
                viewholder.tvChildOut.setText(item.getText());

            }
            break;
            case 1:{
                final MyViewHolderWithChild viewholder= (MyViewHolderWithChild) holder;
                Item item=items.get(position);
                viewholder.setIsRecyclable(false);
                viewholder.tvChild.setText(item.getText());

                viewholder.expandlayout.setInRecyclerView(true);
               // viewholder.expandlayout.setExpanded(expandState.get(position));
                viewholder.expandlayout.setExpanded(true); //auto expand

                viewholder.expandlayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewholder.image,0f,180f).start();
                        expandState.put(position,true);

                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewholder.image,180f,0f).start();
                        expandState.put(position,false);
                    }


                });

                List<String> a= new ArrayList<>();
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                a.add("đ");
                RecyclerExamAdapter adapter=new RecyclerExamAdapter(a,context);
                viewholder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                viewholder.recyclerView.setAdapter(adapter);

                viewholder.image.setRotation(expandState.get(position)?180f:0f);
                viewholder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewholder.expandlayout.toggle();
                    }
                });

                viewholder.tvsubChild.setText(items.get(position).getSubText());
            }
            break;
            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(View button, float from, float to) {
        ObjectAnimator animator=ObjectAnimator.ofFloat(button,"rotation",from,to);
        animator.setDuration(200);
        animator.setInterpolator(Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR));
        return animator;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
