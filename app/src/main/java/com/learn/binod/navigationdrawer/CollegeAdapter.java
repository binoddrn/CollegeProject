package com.learn.binod.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private ArrayList<College> arrayList;
    Context context;
    Cursor cursor;
    DBHelper mydb;


    public CollegeAdapter(ArrayList arrayList, Context contextm,DBHelper mydb) {
        this.arrayList=arrayList;
        this.context=contextm;
        this.mydb=mydb;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.college_list_item,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.collegename.setText(arrayList.get(position).getCollegename());
       holder.root.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,CollegeDetail.class).putExtra("id",arrayList));
           }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView collegename;
        private LinearLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);
            collegename=(TextView)itemView.findViewById(R.id.collegename);
            root=(LinearLayout)itemView.findViewById(R.id.root);
        }
    }
}
