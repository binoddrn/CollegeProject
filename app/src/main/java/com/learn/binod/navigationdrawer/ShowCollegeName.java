package com.learn.binod.navigationdrawer;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.List;

import Adapter.CollegeAdapter;

public class ShowCollegeName extends AppCompatActivity {
    DBHelper mydb;
    private Cursor res;
    private ListView listView;
    private List<DBHelper> helpers;
    private CollegeAdapter adapter;
    private RecyclerView reclyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_college_name);
        mydb=new DBHelper(this);


        reclyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        reclyclerview.setLayoutManager(layoutManager);
        reclyclerview.setHasFixedSize(true);




        show();
    }

    private void show(){
        res= mydb.getCollegeName();
        if (res.getCount()==0){
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("CollegeName: "+res.getString(0));

        }
        adapter=new CollegeAdapter(buffer,ShowCollegeName.this);

       // showMessage("Data",buffer.toString());
    }
    public void showMessage(String title,String Message){
       // AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // builder.setCancelable(true);
      //  builder.setTitle(title);
      //  builder.setMessage(Message);
     //   builder.show();


    }
}
