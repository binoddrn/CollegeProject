package com.learn.binod.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
    Cursor cursor;
    DBHelper mydb;


    public CollegeAdapter(ArrayList arrayList, Context context) {
        this.arrayList=arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.college_list_item,parent,false);
        return new MyViewHolder(view,context,arrayList);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.collegename.setText(arrayList.get(position));
    }
     //   holder.root.setOnClickListener(new View.OnClickListener() {
       //     @Override
            public void onClick(View v) {
           //     Cursor cursor=mydb.getAllData();
             //   arrayList.add("description"+cursor.getString(2));
               // context.startActivity(new Intent(context,CollegeDetail.class).putExtra("description",arrayList));


            //    Intent intent=new Intent(this.context,CollegeDetail.class);
             //   intent.putExtra("Description",cursor.getString(2));
               // intent.putExtra("Address",cursor.getString(3));
               // intent.putExtra("PhoneNumber",cursor.getString(4));
              //  this.context.startActivity(intent);
        //    }
      //  });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView collegename;
        private LinearLayout root;

        ArrayList<String> arrayList=new ArrayList<String>();
        Context context;
        public MyViewHolder(View itemView,Context context,ArrayList<String> arrayList) {
            super(itemView);

            this.arrayList=arrayList;
            this.context=context;
            itemView.setOnClickListener(this);
            collegename=(TextView)itemView.findViewById(R.id.collegename);
            root=(LinearLayout)itemView.findViewById(R.id.root);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Cursor cursor=mydb.getAllData();
            Intent intent=new Intent(this.context,CollegeDetail.class);
              intent.putExtra("Description",cursor.getString(2));
            intent.putExtra("Address",cursor.getString(3));
             intent.putExtra("PhoneNumber",cursor.getString(4));
              this.context.startActivity(intent);




        }
    }
}
