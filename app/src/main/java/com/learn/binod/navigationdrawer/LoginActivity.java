package com.learn.binod.navigationdrawer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView textViewSignup;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private Button login_button;
    String login_url = "http://192.168.100.14/login.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /*if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }*/

        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        login_button = (Button) findViewById(R.id.login_button);
        builder = new AlertDialog.Builder(LoginActivity.this);

        progressDialog = new ProgressDialog(this);
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        login_button.setOnClickListener(this);
    }

    public void userLogin() {
         final String email = editTextEmail.getText().toString();
       final String password = editTextPassword.getText().toString();


        if (email.equals("") || password.equals("")) {
            builder.setTitle("something went wrong");
            DisplayAlert("Enter a valid username and password...");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("login_failed"))
                                {
                                    builder.setTitle("Login Error....");
                                    DisplayAlert(jsonObject.getString("message"));
                                }
                                else
                                {
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("name",jsonObject.getString("name"));
                                    bundle.putString("email",jsonObject.getString("email"));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                                String message = jsonObject.getString("message");
                                builder.setTitle("server response");
                                builder.setMessage(message);
                                DisplayAlert(code);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_LONG).show();
                    error.printStackTrace();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("user_name", email);
                    params.put("password", password);
                    return params;
                }
            };
            MySingleton.getInstance(LoginActivity.this).addToRequestqueue(stringRequest);
        }

}
    public  void DisplayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editTextEmail.setText("");
                editTextPassword.setText("");

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignIn){
            userLogin();
        }
        if (view == textViewSignup) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (view == login_button) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}
