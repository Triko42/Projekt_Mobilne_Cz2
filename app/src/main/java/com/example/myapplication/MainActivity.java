package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Navigator.navigateTo(this.getSupportFragmentManager(), new TestFragment());
    }

    @Override
    public void onBackPressed() {
        if (Navigator.currentScreen().equals(AdminPanelFragment.class.getSimpleName())) {
            Navigator.popBackStack(getSupportFragmentManager());
        }
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}