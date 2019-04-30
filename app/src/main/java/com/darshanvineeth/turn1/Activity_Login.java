package com.darshanvineeth.turn1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity {
     private TextView greet;
    private EditText username;
    private EditText userpassword;
    private Button signin;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get objects
        greet = findViewById(R.id.tv_greet);
        username = findViewById(R.id.userName);
        userpassword = findViewById(R.id.userPassword);
        signin = findViewById(R.id.button_signin);
        signup = findViewById(R.id.sign_up);

        String source = getIntent().getStringExtra("Source");
        if (source.equals("CreateActivity")) {
            greet.setText("Login here");
        }
        //Login
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        //Don't have an account?
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Login.this, CreateActivity.class);
                startActivity(intent);
            }
        });


    }

    private void validate() {

        username = findViewById(R.id.userName);
        userpassword = findViewById(R.id.userPassword);
        signin = findViewById(R.id.button_signin);
        signup = findViewById(R.id.sign_up);

        String user = username.getText().toString();
        String pass = userpassword.getText().toString();
        String user_entry = user + pass;    //key

        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String user_credential = sharedPreferences.getString("user_credential", "");

        if (user_credential.equals("")) {
            closeKeyboard();
            Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            username.setText("");
            userpassword.setText("");
        } else if (user.equals("")) {
            closeKeyboard();
            Toast.makeText(Activity_Login.this, "Please enter Username", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {
            closeKeyboard();
            Toast.makeText(Activity_Login.this, "Please enter Password", Toast.LENGTH_SHORT).show();
        } else if (user_entry.equals(user_credential)) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            editor.putString("log_flag","1");    //flags login
            editor.apply();

            Intent intent = new Intent(Activity_Login.this, DrawerActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            username.setText("");
            userpassword.setText("");
        }
    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
