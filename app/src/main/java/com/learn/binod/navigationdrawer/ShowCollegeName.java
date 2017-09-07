package com.learn.binod.navigationdrawer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowCollegeName extends AppCompatActivity {
    DBHelper mydb;
    private Cursor res;
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
     //   res= mydb.getCollegeName();
        res=mydb.getAllData();
        if (res.getCount()==0){
            showMessage("Error","Nothing Found");
            return;
        }
        ArrayList<College> arrayList = new ArrayList();
       // StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){







      //      buffer.append("CollegeName: "+res.getString(0));
         //   arrayList.add(res.getString(res.getColumnIndex("COLLEGENAME")));

     // String s=      res.getString(res.getColumnIndex("COLLEGENAME"));
           // Log.e("COLLEGNAM",s);
        //    arrayList.add("Address" + res.getString(2));
       //     arrayList.add("Phone Number" + res.getString(3));
       //     arrayList.add("Description" + res.getString(4));

        }
        adapter=new CollegeAdapter(arrayList,ShowCollegeName.this,mydb);
        reclyclerview.setAdapter(adapter);
    //    adapter=new CollegeAdapter(buffer,ShowCollegeName.this);
    //    reclyclerview.setAdapter(adapter);

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
