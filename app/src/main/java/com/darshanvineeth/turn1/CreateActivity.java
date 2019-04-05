package com.darshanvineeth.turn1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements TextWatcher {

    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button btn_login;
    // Email validation and Password match flags
    int email_flag = 0, pass_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        username = findViewById(R.id.newUsername);
        email = findViewById(R.id.newEmail);
        password = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);

        //TextWatchers for Email and Confirm password EditTexts
        email.addTextChangedListener(CreateActivity.this);
        confirmPassword.addTextChangedListener(CreateActivity.this);

        //Create Account button
        Button create_button = findViewById(R.id.button_create);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String userName = username.getText().toString();
                String userPassword = password.getText().toString();
                //Check for Valid email and Password match
                if (email_flag == 0) {
                    email.requestFocus(); //If email not valid
                } else if (pass_flag == 0) {
                    confirmPassword.requestFocus(); //If passwords not match
                } else if (pass_flag == 1 && email_flag==1) {

                    //Create new account on Email valid and Passwords match and go to login page
                    editor.putString("user_credential", userName + userPassword);
                    editor.putString("email", email.getText().toString());
                    editor.apply();
                    Toast.makeText(CreateActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateActivity.this, Activity_Login.class);
                    intent.putExtra("Source", "CreateActivity");
                    startActivity(intent);
                }


            }
        });

        btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateActivity.this, Activity_Login.class);
                intent.putExtra("Source", "CreateActivity1");
                startActivity(intent);
            }
        });


    }


    boolean isEmailValid(CharSequence Email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        String userPassword = password.getText().toString();
        String confirm_password = confirmPassword.getText().toString();

        if (!isEmailValid(email.getText().toString())) {
            email.setError("Please enter a valid email address");
        } else email_flag = 1;

        if (!userPassword.equals(confirm_password)) {
            confirmPassword.setError("Passwords doesn't match");
        } else if (userPassword.equals(confirm_password) && !userPassword.equals("") && !confirm_password.equals("")) {
            pass_flag = 1;
        }

    }
}