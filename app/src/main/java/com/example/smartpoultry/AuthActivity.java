package com.example.smartpoultry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthActivity extends AppCompatActivity {
    Button btmlogin,btncreateaccount,btnforgetpass,btncalllogin,btncallcreateaccount;
    LinearLayout logiform,registerform;
    DbHelper dbHelper;
    EditText Fname,Lname,Email,Phone,Password1,Password2;
    EditText Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        registerform = findViewById(R.id.registration_layout);
        logiform = findViewById(R.id.login_layout);
        btncallcreateaccount = findViewById(R.id.call_registration);
        btncalllogin = findViewById(R.id.call_login);
        btmlogin = findViewById(R.id.btn_login);
        btnforgetpass = findViewById(R.id.call_forgot_password);
        btncreateaccount = findViewById(R.id.create_account);
        Fname = findViewById(R.id.account_firstname);
        Lname = findViewById(R.id.account_lastname);
        Email = findViewById(R.id.account_email);
        Phone = findViewById(R.id.account_phone);
        Password1 = findViewById(R.id.account_password1);
        Password2 = findViewById(R.id.account_password2);
        Username = findViewById(R.id.login_username);
        Password = findViewById(R.id.login_password);

        dbHelper = new DbHelper(this);


        btncallcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getAccountLogin();
                if (cursor.getCount() == 0) {
                    logiform.setVisibility(View.GONE);
                    registerform.setVisibility(View.VISIBLE);
                }else{
                    showMessage("Login ","Account Already Exist Kindly Login");
                    registerform.setVisibility(View.GONE);
                    logiform.setVisibility(View.VISIBLE);
                }
            }
        });
        btncalllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerform.setVisibility(View.GONE);
                logiform.setVisibility(View.VISIBLE);
            }
        });
        btmlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logindetails();
            }
        });
        btncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create account check passwords
                try {
                    Cursor cursor = dbHelper.getAccountLogin();
                    if (cursor.getCount() == 0)
                    {
                        // Toast.makeText(this, "No Chick Flock yet", Toast.LENGTH_SHORT).show();

                    String password = Password1.getText().toString();
                    String confirmPassword = Password2.getText().toString();
                    if(password.equals(confirmPassword)){
                        boolean isInserted = dbHelper.createAccount(Email.getText().toString(),Fname.getText().toString(),Lname.getText().toString(),Phone.getText().toString(),"7/9/2022");
                        if (isInserted == true) {

                            //showMessage("Registration  Success","Message : Account created successfully ");
                            boolean accountCreated = dbHelper.createLoginAccount(Email.getText().toString(),Password1.getText().toString(),"Admin");
                            if(accountCreated == true){
                                showMessage("Registration  Success","Message : Account created successfully ");
                            }else{
                                showMessage("Registration  Error","Message :  Failed to save account details try again");
                            }
                        } else {

                            showMessage("Registration  Error","Message :  Failed to save account details try again");
                        }
                    }else{
                        showMessage("Registration  Error","Password :  Password do not match");
                    }
                    }else{
                        showMessage("Login ","Account Already Exist Kindly Login");
                        registerform.setVisibility(View.GONE);
                        logiform.setVisibility(View.VISIBLE);
                    }

                }catch(Exception ex){
                    // Toast.makeText(ChickenManagement.this, "Error" + ex, Toast.LENGTH_SHORT).show();
                    showMessage("Registration  Error","Message : "+ex.getMessage());

                }
            }
        });

        btnforgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // forgot password
            }
        });


        registerform.setVisibility(View.GONE);

    }

    public void showMessage(String title,String message){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.loginavater));
        builder.setPositiveButton("OK",null);
        builder.show();
    }

    private void logindetails() {
        Cursor cursor = dbHelper.getAccountLogin();
        if (cursor.getCount() == 0)
        {
            // Toast.makeText(this, "No Chick Flock yet", Toast.LENGTH_SHORT).show();
            showMessage("Login ","Create account to login");
        }else{
            if (cursor.moveToNext()){
                String username;
                String password;
                username = cursor.getString(0);
                password = cursor.getString(1);
                if (username.equals(Username.getText().toString())){
                    if (password.equals(Password.getText().toString())){
                        Password.setText("");
                        Username.setText("");
                        Intent intent = new Intent(AuthActivity.this,Dashboard.class);
                        startActivity(intent);
                    } else{
                        showMessage("Login ","Wrong Login Details");
                    }
                }else{
                    showMessage("Login ","Account Do Not Exits");
                }

            }

        }
    }


}
