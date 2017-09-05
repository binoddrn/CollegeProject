package com.learn.binod.navigationdrawer;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowCollegeName extends AppCompatActivity {
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_college_name);
        show();
    }

    private void show(){
       Cursor res= mydb.getCollegeName();
        if (res.getCount()==0){
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("CollegeName: "+res.getString(1));

        }
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
