/*
 * Created By Hendri Saputra on 6/2/17 5:51 PM
 * Copyright (c) 2017. All right reserved.
 *
 * Last modified 6/2/17 5:50 PM
 */

package com.wawa.tesapp.activity;

/**
 * Created by pisua on 6/2/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.MobileAds;
import com.wawa.tesapp.R;
import com.wawa.tesapp.adapter.ImageAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    InterstitialAd interstitialAd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,"ca-app-pub-6747050656813903~1005838911");

        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        AdView adView = (AdView)this.findViewById(R.id.adView);
        adView.loadAd(adRequest);


        gridView = (GridView)findViewById(R.id.clueGrid);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ShowActivity.class);
                i.putExtra("id", position);
                startActivity(i);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.rate:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("market://details?id=" + getPackageName()));
                startActivity(i);
                break;
            case R.id.alapp:
                Intent it = new Intent(Intent.ACTION_VIEW);
                String app_dev = getString(R.string.name_developer_app);
                it.setData(Uri.parse(app_dev));
                startActivity(it);
                break;

            case R.id.about:
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(getString(R.string.title))
                        .setMessage(getString(R.string.email_developer))
                        .setPositiveButton("Oke", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }

        return true;
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
