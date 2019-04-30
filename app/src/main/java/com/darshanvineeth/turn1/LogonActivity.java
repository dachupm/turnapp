package com.darshanvineeth.turn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogonActivity extends AppCompatActivity {

    int flag = 0; //Flag the selected tab(Signin/Signup)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);


        final TextView textView1 = findViewById(R.id.textView1); // Header
        TextView textView2 = findViewById(R.id.textView2); // TODO: Description change

        final Button signin_methods = findViewById(R.id.signin_methods);
        final Button signup_methods = findViewById(R.id.signup_methods);
        final Button sign_email = findViewById(R.id.sign_email);
        final Button sign_phone = findViewById(R.id.sign_phone);
        signin_methods.setBackground(getResources().getDrawable(R.drawable.button_focus)); //Default tab on launch : Sign In


        //Signin
        signin_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = 0; // Flags that methods(Email/Phone) are selected from 'Signin tab'
                signin_methods.setBackground(getResources().getDrawable(R.drawable.button_focus));
                signup_methods.setBackground(getResources().getDrawable(R.drawable.button_normal));
                //Toast.makeText(LogonActivity.this, "Singin Methods", Toast.LENGTH_SHORT).show();

                textView1.setText("Login to your Account");
                sign_email.setText("sign in using email");
                sign_phone.setText("signin using phone number");

            }
        });

        //Signup
        signup_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1; // Flags that methods(Email/Phone) are selected from 'Signup tab'
                signup_methods.setBackground(getResources().getDrawable(R.drawable.button_focus));
                signin_methods.setBackground(getResources().getDrawable(R.drawable.button_normal));
                //Toast.makeText(LogonActivity.this, "Signup Methods", Toast.LENGTH_SHORT).show();

                textView1.setText("Create a \nNew Account");
                sign_email.setText("sign up with email");
                sign_phone.setText("signup with phone number");


            }
        });

        //TODO: Both Signin & SIgnup methods(email & phone) starts  'Activity_Login' and 'CreateActivity' resp. Uses 'Username' and 'Password' instead of email & phone for login.
        sign_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_email();
            }
        });

        sign_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_phone();
            }
        });

    }


    private void sign_email() {
        if (flag == 0) // From Signin
        {
            Intent intent = new Intent(LogonActivity.this, Activity_Login.class);
            intent.putExtra("Source", "LogonActivity");
            startActivity(intent);
        } else //from Signup
        {
            Intent intent = new Intent(LogonActivity.this, CreateActivity.class);
            startActivity(intent);
        }
        finish();
    }

    private void sign_phone() {
        if (flag == 0) //From Signin
        {
            Intent intent = new Intent(LogonActivity.this, Activity_Login.class);
            intent.putExtra("Source", "LogonActivity");
            startActivity(intent);
        } else //from signup
        {
            Intent intent = new Intent(LogonActivity.this, CreateActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
