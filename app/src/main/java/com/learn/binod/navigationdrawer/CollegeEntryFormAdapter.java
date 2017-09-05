package com.learn.binod.navigationdrawer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by binod on 6/17/2017.
 */

public class CollegeEntryFormAdapter extends BaseAdapter {
    DBHelper myDb;
    Context context;
    ArrayList<College> collegeList;

    public CollegeEntryFormAdapter(Context context, ArrayList<College> list) {
        this.context=context;
        collegeList=list;
    }


    @Override
    public int getCount() {
        return collegeList.size();
    }

    @Override
    public Object getItem(int position) {
        return collegeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        College college=collegeList.get(position);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.model,null);

        }
        TextView tvCollegeName = (TextView) convertView.findViewById(R.id.tv_name);
        tvCollegeName.setText(college.getName());
        TextView tvaddress = (TextView) convertView.findViewById(R.id.tv_address);
        tvaddress.setText(college.getAddress());
        TextView tvdesc = (TextView) convertView.findViewById(R.id.tv_description);
        tvdesc.setText(college.getDescription());

        return convertView;
    }
}
