package com.example.sob_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(runnable, 5000);
    }

    Runnable runnable = new Runnable(){

        @Override
        public void run(){
            Intent intent = new Intent(getApplicationContext(), register.class);
            startActivity(intent);
            finish();
        }

    };

}
