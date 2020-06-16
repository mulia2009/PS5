package com.mulia754.detikPS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mulia754.detikPS.bottom.bottom_info;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.buat.PasswordNewNote;
import com.mulia754.detikPS.indonews.MainActivityNews;
import com.mulia754.detikPS.swipe.MainActivitySlide;

public class kontakPS extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    String uriString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_p_s);

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(1000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(1000);
        Button ButtonWa = (Button)findViewById(R.id.btnWa);
        Button ButtonTelegram = (Button)findViewById(R.id.btnTelegram);
        Button ButtonInstagram = (Button)findViewById(R.id.btnInstagram);
        Button ButtonEmail = (Button)findViewById(R.id.btnEmail);
        Button ButtonTelepon = (Button)findViewById(R.id.btnTelepon);
        Button buttonShareWA = (Button)findViewById(R.id.btnsharewa);
        Button buttonShareTele = (Button)findViewById(R.id.btnShareTelegram);
        Button buttonBuat = (Button)findViewById(R.id.btnBuat);


        final TextView  tvNoHP = findViewById(R.id.tvNoHP);


        buttonShareWA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                uriString = "https://play.google.com/store/apps/details?id=com.mulia754.ps5\n" ;
                sharingIntent.putExtra(Intent.EXTRA_TEXT, uriString);
                sharingIntent.setPackage("com.whatsapp");
                startActivity(sharingIntent);
            }
        });

        buttonShareTele.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                uriString ="https://play.google.com/store/apps/details?id=com.mulia754.ps5" +
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, uriString);
                sharingIntent.setPackage("org.telegram.messenger");
                startActivity(sharingIntent);
            }
        });

        buttonBuat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentku = new Intent(kontakPS.this, PasswordNewNote.class);
                startActivity(intentku);
            }
        });


                ButtonWa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=628114982009&text= Assalamualaikum..."));
                startActivity(intent);
            }
        });


        ButtonTelegram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://t.me/mulia17x" ));
                startActivity(intent);
            }
        });

        ButtonInstagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentku2 = new Intent();
                intentku2.setAction(Intent.ACTION_VIEW);
                intentku2.addCategory(Intent.CATEGORY_BROWSABLE);
                intentku2.setData(Uri.parse("https://instagram.com/akmil2009?igshid=169peik1lstf7"));
                intentku2.setPackage("com.instagram.android");
                startActivity(intentku2);
            }
        });

        ButtonTelepon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String toDial="tel:"+tvNoHP.getText().toString();

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));

            }
        });

        ButtonEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "muliaadidharma@gmail.com", null));

                startActivity(Intent.createChooser(emailIntent, "Assalamualaikum"));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.contact);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.videox:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_videox.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivitySlide.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;


                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_info.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.akun:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivityNews.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.contact:

                        return true;
                }

                return false;
            }
        });

    }





    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}

