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

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.wawa.tesapp.R;
import com.wawa.tesapp.adapter.ImageAdapter;

import android.view.View.OnClickListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import com.wawa.tesapp.touch.TouchImageView;
import com.wawa.tesapp.touch.TouchImageView.OnTouchImageViewListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ShowActivity extends AppCompatActivity  {
    Button save, share, set;
    private TouchImageView image;
    private DecimalFormat df;
    InterstitialAd interstitialAd = null;

    public static boolean isSDCARDAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)? true :false;
    }
    File myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Gallery M/");
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        AdView adView = (AdView)this.findViewById(R.id.adView);
        adView.loadAd(adRequest);
        final Context mContext ;
        mContext=this;

        set = (Button) findViewById(R.id.bSet);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        final int position = i.getExtras().getInt("id");
        final ImageAdapter imageAdapter = new ImageAdapter(this);
        df = new DecimalFormat("#.##");
        image = (TouchImageView) findViewById(R.id.img);
        image.setImageResource(imageAdapter.mThumbIds[position]);

        image.setOnTouchImageViewListener(new OnTouchImageViewListener() {

            @Override
            public void onMove() {
                PointF point = image.getScrollPosition();
                RectF rect = image.getZoomedRect();
                float currentZoom = image.getCurrentZoom();
                boolean isZoomed = image.isZoomed();
            }
        });




        set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                 Retrieve a WallpaperManager
                WallpaperManager myWallpaperManager = WallpaperManager
                        .getInstance(ShowActivity.this);

                try {
                    // Change the current system wallpaper
                    myWallpaperManager.setResource(imageAdapter.mThumbIds[position]);

                    // Show a toast message on successful change
                    Toast.makeText(ShowActivity.this,
                            "Wallpaper successfully changed", Toast.LENGTH_SHORT)
                            .show();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }

    }

}
