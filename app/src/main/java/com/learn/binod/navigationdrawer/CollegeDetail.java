package com.learn.binod.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CollegeDetail extends AppCompatActivity {
    TextView description,address,phonenumber;
    String desc,addr,phno,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        description=(TextView)findViewById(R.id.DescriptionAboutCollege);
        address=(TextView)findViewById(R.id.AddressOfCollege);
        phonenumber=(TextView)findViewById(R.id.PhoneNumberOfCollege);

       id =getIntent().getStringExtra("id");

     //   addr=getIntent().getStringExtra("Address");
     //   phno=getIntent().getStringExtra("PhoneNumber");
        description.setText(desc);
       // address.setText(addr);
     //   phonenumber.setText(phno);
       // description.setText("Description"+getIntent().getStringExtra("Description"));
     //   address.setText("Address"+getIntent().getStringExtra("Address"));
       // phonenumber.setText("PhoneNumber : "+getIntent().getStringExtra("PhoneNumber"));

    }
}
