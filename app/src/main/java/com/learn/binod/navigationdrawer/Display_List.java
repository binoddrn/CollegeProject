package com.learn.binod.navigationdrawer;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

import static com.learn.binod.navigationdrawer.DBHelper.COL_2;
import static com.learn.binod.navigationdrawer.DBHelper.TABLE_NAME;


public class Display_List extends AppCompatActivity {
    DBHelper myDb;
    ListView mistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__list);

        mistView=(ListView) findViewById(R.id.listView1);
        myDb=new DBHelper(this);
        showlist();
    }

    private void showlist(){
        ArrayList<College> collegelist=new ArrayList<College>();
        collegelist.clear();
        String query="SELECT FROM colleges WHERE collegename='"+COL_2+"' ";
        Cursor c1 = myDb.selectQuery(query);
        if (c1 != null && c1.getCount() != 0){
            if (c1.moveToFirst()) {
                do {
                    College college = new College();
                    college.setName(c1.getString(c1
                            .getColumnIndex("collegename")));
                    college.setAddress(c1.getString(c1
                            .getColumnIndex("address")));
                    college.setName(c1.getString(c1
                            .getColumnIndex("description")));

                } while (c1.moveToNext());
            }
        }
        CollegeEntryFormAdapter collegeEntryFormAdapter=new
                CollegeEntryFormAdapter(Display_List.this,collegelist);
        mistView.setAdapter(collegeEntryFormAdapter);
    }
}

