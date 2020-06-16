package com.mulia754.detikPS.buat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mulia754.detikPS.R;

public class buatBerita extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_berita);



        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(1000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(1000);
        Button ButtonManual = (Button)findViewById(R.id.btnManual);
        Button ButtonLink = (Button)findViewById(R.id.btnIsiLink);
        Button ButtonWeb = (Button)findViewById(R.id.btnBuatWeb);
        Button ButtonVideo = (Button)findViewById(R.id.btnVideo);
       Button buttonMiliter = (Button)findViewById(R.id.btnMiliter);
        Button buttonPersit = (Button)findViewById(R.id.btnPersit);
        Button buttonPolitik = (Button)findViewById(R.id.btnPolitik);
        Button buttonOlahraga = (Button)findViewById(R.id.btnOlahraga);
        Button buttonLain = (Button)findViewById(R.id.btnLain);



        buttonMiliter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatTerbaru.class);
                startActivity(intent);
            }
        });
        buttonPersit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatBeritaPersit.class);
                startActivity(intent);
            }
        });
        buttonPolitik.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatBeritaPolitik.class);
                startActivity(intent);
            }
        });
        buttonOlahraga.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatBeritaOlahraga.class);
                startActivity(intent);
            }
        });
        buttonLain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatBeritaLainLain.class);
                startActivity(intent);
            }
        });


        ButtonWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, newCatatan.class);
                startActivity(intent);
            }
        });




        ButtonVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(buatBerita.this, buatVideo.class);

                startActivity(intent);

            }
        });
     ButtonManual.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(buatBerita.this, buatManualItemSaja.class);

            startActivity(intent);

        }
    });

        ButtonLink.setOnClickListener(v -> {
            Intent intent = new Intent(buatBerita.this, NewNoteActivity.class);

            startActivity(intent);

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

