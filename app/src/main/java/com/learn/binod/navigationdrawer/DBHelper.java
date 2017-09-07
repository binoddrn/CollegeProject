package com.learn.binod.navigationdrawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by binod on 6/16/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqlDatabase;
    public static final String DATABASE_NAME = "College.db";
    public static final String TABLE_NAME = "colleges";
    public static final String COL_1 = "id";
    public  static final String COL_2 = "collegename";
    public static final String COL_3 = "address";
    public static final String COL_4 = "description";
    public static final String COL_5 = "phonenumber";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,COLLEGENAME TEXT,ADDRESS TEXT,DESCRIPTION TEXT,PHONENUMBER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String collegename,String address,String description,String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,collegename);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,description);
        contentValues.put(COL_5,phonenumber);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

  /*  public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }*/
    public Cursor getCollegeName(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select COLLEGENAME from " +TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String collegename,String address,String description,String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,collegename);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,description);
        contentValues.put(COL_5,phonenumber);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public ArrayList<College>
    getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<College> arrayList = new ArrayList<>();
        if (res.getCount()>0) {
            res.moveToFirst();
            if (res.moveToFirst()){
                do {
                    College college = new College();
                    college.setId(res.getString(0));
                    college.setCollegename(res.getString(1));
                    college.setAddress(res.getString(2));
                    college.setDescription(res.getString(3));
                    arrayList.add(college);
                } while (res.moveToNext());
            }
        }
        res.close();
        return arrayList;
    }

    public College getDetailFromId(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID='"+id+"'", null);
        College college = new College();
        if (res.getCount() > 0) {
            if (res.moveToFirst()) {
                college.setId(res.getString(0));
                college.setCollegename(res.getString(1));
                college.setAddress(res.getString(2));
                college.setDescription(res.getString(3));
            }
        }
        res.close();
        return college;
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Cursor selectQuery(String query) {
        Cursor c1 = null;
        try {

            if (sqlDatabase.isOpen()) {
                sqlDatabase.close();

            }
            sqlDatabase = this.getWritableDatabase();
            c1 = sqlDatabase.rawQuery(query, null);

        } catch (Exception e) {

            System.out.println("DATABASE ERROR " + e);

        }
        return c1;
    }
}
