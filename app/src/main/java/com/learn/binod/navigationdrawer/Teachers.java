package com.learn.binod.navigationdrawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.learn.binod.navigationdrawer.R.id.editName;
import static com.learn.binod.navigationdrawer.R.id.editRollno;

public class Teachers extends Activity implements View.OnClickListener {
    EditText designation,qualification,fullaname;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShowInfo;
    SQLiteDatabase db;
    //this class is just a practice.... it works... another class added insted of this class Teacher_Detail.java


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        fullaname=(EditText)findViewById(R.id.editTextFullName);
        designation=(EditText) findViewById(R.id.editTextDesignation);
        qualification=(EditText)findViewById(R.id.editTextQualification);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
        db=openOrCreateDatabase("CollegeInfo", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS teachers(FullName TEXT,Designation TEXT,Qualification TEXT);");
    }

    @Override
    public void onClick(View view) {
        if(view==btnAdd)
        {
            if(designation.getText().toString().trim().length()==0||
                    qualification.getText().toString().trim().length()==0||fullaname.getText().toString().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO teachers VALUES('"+fullaname.getText()+"','"+designation.getText()+"','"+qualification.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        fullaname.setText("");
        designation.setText("");
        qualification.setText("");
    }
}
