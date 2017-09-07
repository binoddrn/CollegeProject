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
    DBHelper dbHelper;
    private Cursor res;
    private CollegeAdapter adapter;
    private RecyclerView reclyclerview;
    ArrayList<College> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_college_name);
        dbHelper=new DBHelper(this);

        arrayList = new ArrayList();
        reclyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        reclyclerview.setLayoutManager(layoutManager);
        reclyclerview.setHasFixedSize(true);

        show();
    }

    private void show(){
        arrayList=dbHelper.getAllData();
        adapter=new CollegeAdapter(arrayList,ShowCollegeName.this,dbHelper);
        reclyclerview.setAdapter(adapter);

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
