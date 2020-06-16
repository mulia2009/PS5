package com.mulia754.detikPS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.mulia754.detikPS.swipe.MainActivitySlide;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Starts the About Screen Activity
                Intent i = new Intent(getBaseContext(), MainActivitySlide.class);
                i.putExtra("isPertama", true);
                startActivity(i);
            }
        }, 3000L);

    }


}
