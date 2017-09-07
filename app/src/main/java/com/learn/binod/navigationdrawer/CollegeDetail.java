package com.learn.binod.navigationdrawer;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.learn.binod.navigationdrawer.DBHelper.TABLE_NAME;

public class CollegeDetail extends AppCompatActivity {
    SQLiteDatabase db;
    TextView description,address,phonenumber;
    DBHelper dbHelper;
    String desc,addr,phno,id;
    DBHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        description=(TextView)findViewById(R.id.DescriptionAboutCollege);
        address=(TextView)findViewById(R.id.AddressOfCollege);
        phonenumber=(TextView)findViewById(R.id.PhoneNumberOfCollege);

        id =getIntent().getStringExtra("id");
        College college=new DBHelper(CollegeDetail.this).getDetailFromId(id);
        description.setText(college.getDescription());
        address.setText(college.getAddress());
        phonenumber.setText(college.getPhonenumber());
    }
}
