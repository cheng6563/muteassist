package com.github.cheng6563.muteassist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        MyApplication.INSTANCE.switchMute(this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}