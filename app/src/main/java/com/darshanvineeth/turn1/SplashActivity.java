package com.darshanvineeth.turn1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Log status check
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        String log_flag = sharedPreferences.getString("log_flag","");
        if (log_flag.equals("0")){
            splash(StartActivity.class);
        } else if(log_flag.equals("1")){
            splash(DrawerActivity.class);
        }

    }

    public void splash(final Class nextActivity){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, nextActivity);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
