package com.example.pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            public void run(){
                super.run();
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
