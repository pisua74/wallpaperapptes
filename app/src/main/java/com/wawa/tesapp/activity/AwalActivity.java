package com.wawa.tesapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wawa.tesapp.R;

public class AwalActivity extends AppCompatActivity {
    private Button btnNext;
    private Button btnLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnLink = (Button) findViewById(R.id.btn_link);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(AwalActivity.this, MainActivity.class);
                startActivity(pindah);
            }
        });

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("")));
                } catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("")));

                }
            }
        });
    }
}
