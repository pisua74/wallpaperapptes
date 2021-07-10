/*
 * Created By Hendri Saputra on 6/2/17 5:51 PM
 * Copyright (c) 2017. All right reserved.
 *
 * Last modified 6/2/17 5:51 PM
 */

package com.wawa.tesapp.activity;

/**
 * Created by pisua on 6/2/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.wawa.tesapp.R;

public class SplashActivity extends Activity {
    protected int _splashTime = 2000;

    private Thread splashTread;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        layar penuh
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        final SplashActivity sPlashScreen = this;

        // thread for displaying the SplashScreen
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait(_splashTime);
                    }

                } catch(InterruptedException e) {}
                finally {
                    finish();

                    Intent i = new Intent();
                    i.setClass(SplashActivity.this, AwalActivity.class);
                    startActivity(i);

                    try {
                        sleep(_splashTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };

        splashTread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized(splashTread){
                splashTread.notifyAll();
            }
        }
        return true;
    }
}