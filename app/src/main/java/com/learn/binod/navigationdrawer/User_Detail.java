package com.learn.binod.navigationdrawer;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Detail extends AppCompatActivity {
    UsersDBHelper myDb;
    private EditText edtID,edtfullname,edtaddress,edtemail,edtphonenumber;
    private Button btnadd,btnupdate,btndelete,btnviewall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__detail);

        myDb=new UsersDBHelper(this);
        edtID=(EditText)findViewById(R.id.editText_ID);
        edtfullname=(EditText)findViewById(R.id.editText_FullName);
        edtaddress=(EditText)findViewById(R.id.editText_Address);
        edtemail=(EditText)findViewById(R.id.editText_Email);
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
                            Toast.makeText(User_Detail.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(User_Detail.this,"Data not Deleted",Toast.LENGTH_LONG).show();
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
                                edtfullname.getText().toString(),
                                edtaddress.getText().toString(),
                                edtemail.getText().toString(),
                                edtphonenumber.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(User_Detail.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(User_Detail.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(edtfullname.getText().toString(),
                                edtaddress.getText().toString(),
                                edtemail.getText().toString(),
                                edtphonenumber.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(User_Detail.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(User_Detail.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewall.setOnClickListener(
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
                            buffer.append("FullName :"+ res.getString(1)+"\n");
                            buffer.append("Designation :"+ res.getString(2)+"\n");
                            buffer.append("Qualification :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void Clear(){

        // edtID.setText("");
        edtfullname.setText("");
        edtaddress.setText("");
        edtemail.setText("");
        edtphonenumber.setText("");
    }
}
