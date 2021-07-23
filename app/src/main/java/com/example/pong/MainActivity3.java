package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pong.views.custom1;
import com.example.pong.views.custom_view;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new custom_view(this));
    }
}