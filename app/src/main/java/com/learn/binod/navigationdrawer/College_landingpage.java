package com.learn.binod.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class College_landingpage extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mref;
    private ImageView college_profile_pic;
    ArrayList<College> colleges;
    private TextView address;
    private TextView description;
    private TextView name;


    private Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_landingpage);

        mref = FirebaseDatabase.getInstance().getReference();
        address = (TextView) findViewById(R.id.address);
        college_profile_pic = (ImageView) findViewById(R.id.college_profile_pic);
        description = (TextView) findViewById(R.id.description);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        colleges=new ArrayList<>();
        name=(TextView) findViewById(R.id.name) ;

        mref.child("Colleges").addChildEventListener(new ChildEventListener() {
                                                         @Override
                                                         public void onChildAdded(DataSnapshot snapshot, String s) {
                                                             if (snapshot.hasChildren()) {
                                                                 for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                                     College college = postSnapshot.getValue(College.class);
                                                                     String add = "Address :" + college.getAddress() + "\n";
                                                                     String descr = "\n Description" + college.getDescription() + "\n\n";

                                                                     address.setText(add);
                                                                     description.setText(descr);

                                                                 }
                                                             }
                                                         }

                                                         @Override
                                                         public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                                            String key=dataSnapshot.getKey();
                                                             College newcollege=dataSnapshot.getValue(College.class);
                                                             for(College cl:colleges){
                                                                 if (cl.getKey().equals(key)){
                                                                     cl.setValues(newcollege);
                                                                     break;
                                                                 }
                                                             }

                                                         }

                                                         @Override
                                                         public void onChildRemoved(DataSnapshot dataSnapshot) {

                                                         }

                                                         @Override
                                                         public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                         }

                                                         @Override
                                                         public void onCancelled(DatabaseError databaseError) {
                                                             System.out.println("The read failed: " + databaseError.getMessage());
                                                         }
        }



        );



    }
    public void update(College college ,String newaddress, String newdescription) {
        college.setAddress(newaddress);
        college.setDescription(newdescription);
       mref.child("colleges").child(college.getKey()).setValue(college);
      //  mref.child("colleges").child("address").setValue(newaddress);
       // mref.child("colleges").child("description").setValue(newdescription);
        //mref.child("colleges").child("name").setValue(newname);
      //  mref.child("colleges").child(college.getKey()).child("name").setValue(newname);
      //  mref.child("colleges").child(college.getKey()).child("address").setValue(newaddress);
      //  mref.child("colleges").child(college.getKey()).child("description").setValue(newdescription);
    }

    @Override
    public void onClick(View v) {

    }


    //@Override
 //////   public void onClick(View v) {
     //   if (v==btnupdate)
      //  {
       //     College f = new College();
      //      f.setKey(String);
       // }
      //      update(,address,description);
   // }//
}

