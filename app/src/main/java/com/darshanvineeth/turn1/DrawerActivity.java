package com.darshanvineeth.turn1;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {


    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bnav_view);
        bottomNav.setOnNavigationItemSelectedListener(bnavListener);

        //Navigation Drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener);

        //Hamburger
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close); //for speech
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Initial State Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    //Navigation drawer menu item select
    private NavigationView.OnNavigationItemSelectedListener navListener = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    toolbar.setTitle("Home");
                    break;
                case R.id.nav_track:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrackFragment()).commit();
                    toolbar.setTitle("Track Activity");
                    break;
                case R.id.nav_settings:
                    //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                    Intent intent1 = new Intent(DrawerActivity.this, SettingsFragment.class);
                    startActivity(intent1);
                    //  toolbar.setTitle("Settings");
                    break;
                case R.id.nav_aboutus:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutusFragment()).commit();
                    toolbar.setTitle("About Us");
                    break;
                case R.id.nav_contact:
                    Toast.makeText(DrawerActivity.this, "info@smartapp.in", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_share:
                    Toast.makeText(DrawerActivity.this, "Share", Toast.LENGTH_SHORT).show();

                    //Share
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String shareSub = "@string/share_sub";
                    String shareBody = "This is G-Turn Smart App";
                    intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(intent, "Share using"));

            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener bnavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.bnav_home:
                    selectedFragment = new HomeFragment();
                    toolbar.setTitle("Home");
                    break;
                case R.id.bnav_scan:
                    selectedFragment = new OverviewFragment();
                    toolbar.setTitle("Overview");
                    break;
                case R.id.bnav_profile:
                    selectedFragment = new ProfileFragment();
                    toolbar.setTitle("Profile");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
