package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pong.views.custom1;
import com.example.pong.views.custom2;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(new custom2(this));
    }
}