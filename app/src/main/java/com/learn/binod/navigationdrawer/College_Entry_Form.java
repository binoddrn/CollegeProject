package com.learn.binod.navigationdrawer;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class College_Entry_Form extends AppCompatActivity {
    DBHelper myDb;
    private TextView tv1,tv2,tv3,tv4;
    private EditText edtID,edtCollegeName,edtaddress,edtdescription,edtphonenumber;
    private Button btnadd,btnupdate,btndelete,btnviewall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__entry__form);
        myDb=new DBHelper(this);

        tv1=(TextView) findViewById(R.id.textView_CollegeName);
        tv2=(TextView) findViewById(R.id.textViewAddress);
        tv3=(TextView) findViewById(R.id.textView_description);
        tv4=(TextView) findViewById(R.id.textView_id);
        edtID=(EditText)findViewById(R.id.editText_ID);
        edtCollegeName=(EditText) findViewById(R.id.editText_College_Name);
        edtaddress=(EditText) findViewById(R.id.editText_address);
        edtdescription=(EditText) findViewById(R.id.editText_description);
        edtphonenumber=(EditText)findViewById(R.id.editText_PhoneNumber);
        btnadd=(Button)findViewById(R.id.button_add);
        btnupdate=(Button)findViewById(R.id.button_update);
        btndelete=(Button)findViewById(R.id.button_delete);
        btnviewall=(Button)findViewById(R.id.button_viewALL);
        AddData();
        Clear();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(edtID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(College_Entry_Form.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(College_Entry_Form.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(edtID.getText().toString(),
                                edtCollegeName.getText().toString(),
                                edtaddress.getText().toString(),
                                edtdescription.getText().toString(),edtphonenumber.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(College_Entry_Form.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(College_Entry_Form.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(edtCollegeName.getText().toString(),
                                edtaddress.getText().toString(),
                                edtdescription.getText().toString(),edtphonenumber.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(College_Entry_Form.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(College_Entry_Form.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void Clear(){

    }


    public void viewAll() {
       /* btnviewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Address :"+ res.getString(2)+"\n");
                            buffer.append("Description :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );*/
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
