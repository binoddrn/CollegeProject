package com.learn.binod.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by binod on 4/17/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context c;
    ArrayList<College> colleges;
    DatabaseReference db;



    public MyAdapter(Context c, ArrayList<College> colleges) {
        this.c = c;
        this.colleges = colleges;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTxt.setText(colleges.get(position).getName());
        holder.addressTxt.setText(colleges.get(position).getAddress());
        holder.descTxt.setText(colleges.get(position).getDescription());
    }

    @Override
    public int getItemCount()
    {
        return colleges.size();
    }
}
