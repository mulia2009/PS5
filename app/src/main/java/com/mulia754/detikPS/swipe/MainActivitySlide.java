package com.mulia754.detikPS.swipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.bottom.bottom_info;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.indonews.MainActivityNews;
import com.mulia754.detikPS.kontakPS;
import com.mulia754.detikPS.ui.SectionsPagerAdapter;

public class MainActivitySlide extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener {

    private InterstitialAd interstitial;

    private long exitTime = 0;

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.aaadepan_main );
        //   initToolBar();
        // initViewPager();
        //initTabLayout();


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( this, getSupportFragmentManager() );
        ViewPager viewPager = findViewById( R.id.viewpager_home );
        viewPager.setAdapter( sectionsPagerAdapter );
        TabLayout tabs = findViewById( R.id.tabs_home );
        tabs.setupWithViewPager( viewPager );



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_info.class));
                        overridePendingTransition(0,0);
                        finish(); return true;

                    case R.id.home:
                        return true;
                    case R.id.videox:
                        startActivity(new Intent(getApplicationContext(),           bottom_videox.class));
                        overridePendingTransition(0,0);
                        finish();   return true;
                    case R.id.akun:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivityNews.class));
                        overridePendingTransition(0,0);
                        finish();    return true;
                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),
                                kontakPS.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.info:
                startActivity(new Intent(getApplicationContext(),
                        bottom_info.class));
                overridePendingTransition(0,0);
                finish(); return true;

            case R.id.home:
                return true;
            case R.id.videox:
                startActivity(new Intent(getApplicationContext(),           bottom_videox.class));
                overridePendingTransition(0,0);
                finish();   return true;
            case R.id.akun:
                startActivity(new Intent(getApplicationContext(),
                        MainActivityNews.class));
                overridePendingTransition(0,0);
                finish();    return true;
        }
        return false;
    }

    }
