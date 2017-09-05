package com.learn.binod.navigationdrawer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.password;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //defining view objects
    private EditText Email;
    private EditText Password;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    private TextView textViewSignin;
    private AlertDialog.Builder builder;
    private EditText FullName;
    private EditText UserName;
    private EditText ConfirmPassword;
    String reg_url="http://192.168.100.14/register.php";


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
       // firebaseAuth = FirebaseAuth.getInstance();

        /*if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }*/

        //initializing views
        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        FullName=(EditText)findViewById(R.id.editTextFullName);
        UserName=(EditText)findViewById(R.id.editTextUserName);
        ConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);


        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        builder= new AlertDialog.Builder(RegisterActivity.this);
    }

    private void registerUser(){

        //getting email and password from edit texts
        final String FulName=FullName.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String userName=UserName.getText().toString().trim();
        final String password  = Password.getText().toString().trim();
        String confirmpassword=ConfirmPassword.getText().toString().trim();


        if(FulName.equals("")||email.equals("")||userName.equals("")||password.equals("")||confirmpassword.equals(""))
        {
            builder.setTitle("something went wrong");
            builder.setMessage("Please fill in the required field");
            displayAlert("input_error");


        }
        else
        {
            if (!(password.equals(confirmpassword)))
            {
                builder.setTitle("something went wrong");
                builder.setMessage("your passwords are not matching");
                displayAlert("input_error");
            }
            else
            {
                StringRequest stringRequest= new StringRequest(Request.Method.POST, reg_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String message=jsonObject.getString("message");
                                    builder.setTitle("server response");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("name",FulName);
                        params.put("email",email);
                        params.put("user_name",userName);
                        params.put("password",password);
                        return params;
                    }
                };
                MySingleton.getInstance(RegisterActivity.this).addToRequestqueue(stringRequest);
            }
        }


        //checking if email and passwords are empty
        /*if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }/*

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
      /*  firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });     */

    }
    public void displayAlert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals("input_errors"))
                {
                    Password.setText("");
                    ConfirmPassword.setText("");
                }
                else if (code.equals("reg_sucess"))
                {
                    finish();
                }
                else if(code.equals("reg_failed"))
                {
                    FullName.setText("");
                    Email.setText("");
                    UserName.setText("");
                    Password.setText("");
                    ConfirmPassword.setText("");
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        //calling register method on click
        if (view==buttonSignup){
        registerUser();
        }

        if (view==textViewSignin){
            //open the login activity when user taps on the already registered textview
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}