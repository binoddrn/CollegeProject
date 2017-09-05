package com.learn.binod.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import java.util.ArrayList;

public class CollegeForm extends AppCompatActivity {
    DatabaseReference db;
    FirebaseHelper helper;
    MyAdapter adapter;
    RecyclerView rv;
    Button saveBtn;
    Button updateBtn;
    EditText nameEditTxt, addressTxt, descTxt;
    private String userId;
    //ArrayList<College> colleges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_form);


        //INITIALIZE RV
        rv = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mlayoutManager);

        //initialize db
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        //adapter
        adapter = new MyAdapter(this, helper.retrieve());
        rv.setAdapter(adapter);

        nameEditTxt = (EditText) findViewById(R.id.nameEditText);
        addressTxt = (EditText) findViewById(R.id.addressEditText);
        descTxt = (EditText) findViewById(R.id.descEditText);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        updateBtn= (Button) findViewById(R.id.updateBtn);



       updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditTxt.getText().toString();
                String address = addressTxt.getText().toString();
                String desc = descTxt.getText().toString();

                updateUser(name,address,desc);
            }
        });




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditTxt.getText().toString();
                String address = addressTxt.getText().toString();
                String desc = descTxt.getText().toString();

                //SET DATA
                College c = new College();
                c.setName(name);
                c.setAddress(address);
                c.setDescription(desc);

                //SAVE
                if (name != null && name.length() > 0) {
                    if (helper.save(c)) {
                        nameEditTxt.setText("");
                        addressTxt.setText("");
                        descTxt.setText("");

                        adapter = new MyAdapter(CollegeForm.this, helper.retrieve());
                       rv.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(CollegeForm.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void updateUser(String name, String address,String description) {
       //  updating the user via child nodes
        if (!TextUtils.isEmpty(name))
          db.child("colleges").child("address").setValue(name);
            if (!TextUtils.isEmpty(address))
            db.child("colleges").child("address").setValue(address);
        if (!TextUtils.isEmpty(description))
            db.child("colleges").child("description").setValue(description);
    }
}



