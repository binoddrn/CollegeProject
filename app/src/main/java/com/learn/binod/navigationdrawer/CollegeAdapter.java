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

    private ArrayList<String> arrayList;
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
        holder.collegename.setText(arrayList.get(position));
       holder.root.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
      //         mydb=new DBHelper(this);
                 cursor=mydb.getAllData();
                arrayList.add("description"+cursor.getString(0));
          //  String g= cursor.getString(1).toString();
           //   Log.e("id is ",g);
                context.startActivity(new Intent(context,CollegeDetail.class).putExtra("id",arrayList));

            //    Intent intent=new Intent(this.context,CollegeDetail.class);
             //   intent.putExtra("Description",cursor.getString(2));
               // intent.putExtra("Address",cursor.getString(3));
               // intent.putExtra("PhoneNumber",cursor.getString(4));
              //  this.context.startActivity(intent);
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
