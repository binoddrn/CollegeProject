package com.learn.binod.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by binod on 9/5/2017.
 */

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.MyViewHolder>{

    private ArrayList<String> arrayList;
    Context context;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.college_list_item,parent,false);
        return new MyViewHolder(view);
    }

    public CollegeAdapter(ArrayList arrayList, Context context) {
        this.arrayList=arrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.collegename.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView collegename;
        private LinearLayout root;
        public MyViewHolder(View itemView) {
            super(itemView);
            collegename=(TextView)itemView.findViewById(R.id.collegename);
            root=(LinearLayout)itemView.findViewById(R.id.root);
        }
    }
}
