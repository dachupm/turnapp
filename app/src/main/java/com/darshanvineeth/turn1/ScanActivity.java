package com.darshanvineeth.turn1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

//        SharedPreferences sharedPreferences = getSharedPreferences("log_data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Splash
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ScanActivity.this,DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);


    }
}
