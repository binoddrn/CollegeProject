package com.learn.binod.navigationdrawer;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by binod on 4/17/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved=null;
    ArrayList <College> colleges= new ArrayList<>();

    /*
     PASS DATABASE REFRENCE
 */

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL

    public Boolean save(College college){
        if (college==null){
            saved=false;
        }
        else {
            try {
                db.child("Colleges").push().setValue(college);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchdata(DataSnapshot dataSnapshot)
    {
        colleges.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            College college= ds.getValue(College.class);
            colleges.add(college);
        }
    }
    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<College> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchdata(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key=dataSnapshot.getKey();
                College newcollege=dataSnapshot.getValue(College.class);
                for (College college:colleges){
                    if (college.getKey().equals(key))
                        college.setValues(newcollege);
                    break;
                }
                fetchdata(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return colleges;
    }

}
