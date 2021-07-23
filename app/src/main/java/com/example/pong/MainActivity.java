 package com.example.pong;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.pong.views.custom1;
import com.example.pong.views.custom_view;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
button.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        custom_view custom =new custom_view(this);
        setContentView(custom);
    }
});
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                custom1 custom1 =new custom1(this);
                setContentView(custom1);
            }
        });
    }
}