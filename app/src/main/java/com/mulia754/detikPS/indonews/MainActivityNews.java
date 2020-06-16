package com.mulia754.detikPS.indonews;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.mulia754.detikPS.MainActivity;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.beritaUmum;
import com.mulia754.detikPS.bottom.bottom_info;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.corona.activities.MainActivityCorona;
import com.mulia754.detikPS.kontakPS;
import com.mulia754.detikPS.swipe.MainActivitySlide;

import java.util.Calendar;
import java.util.Date;

public class MainActivityNews extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout Relativs;
    private LinearLayout LinDetik;
    private AnimationDrawable animationDrawable;
    private InterstitialAd interstitial;

    MaterialCardView cvHead, cvSports, cvTechno, cvBusiness, cvHealth, cvEntertaiment;
    TextView tvToday,tvDetik,tvKompas,tvTribun,tvOkezone,tvLiputan,tvMI,tvRepublika,tvCNN,
            tvSindo,tvAntara,tvTempo,tvJakartaPos,tvviva,tvJpnn,tvBolanet,tvBolacom,tvGoal,tvOtosia,tvOto,tvSuara,
            stTrans,stTrans7,stTvone,stRTV,stRcti,stGlobal,stTVRI,stAntara,stNet,stInews,stMetro,stIndosiar,stKompas,stSCTV,stPrambors,stMNC;
    String hariIni;
    String urlx = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indonews);

        Button ButtonCorona = findViewById(R.id.btnCorona);

        ButtonCorona.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityNews.this, MainActivityCorona.class);

            startActivity(intent);

        });
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest2);

        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }

            public void displayInterstitial() {

                if (interstitial.isLoaded()) {
                    interstitial.show();
                }

            }
        });
        // init constraintLayout
        Relativs = findViewById(R.id.Relativs);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) Relativs.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(5000);

        cvHead = findViewById(R.id.cardHeadLine);
        cvSports = findViewById(R.id.cardSports);
        cvTechno = findViewById(R.id.cardTechno);
        cvBusiness = findViewById(R.id.cardBusiness);
        cvHealth = findViewById(R.id.cardHealth);
        cvEntertaiment = findViewById(R.id.cardEnter);

        cvHead.setOnClickListener(this);
        cvSports.setOnClickListener(this);
        cvTechno.setOnClickListener(this);
        cvBusiness.setOnClickListener(this);
        cvHealth.setOnClickListener(this);
        cvEntertaiment.setOnClickListener(this);
        tvToday = findViewById(R.id.tvDate);

        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")) {
            hariIni = "Minggu";
        } else if (hariIni.equalsIgnoreCase("monday")) {
            hariIni = "Senin";
        } else if (hariIni.equalsIgnoreCase("tuesday")) {
            hariIni = "Selasa";
        } else if (hariIni.equalsIgnoreCase("wednesday")) {
            hariIni = "Rabu";
        } else if (hariIni.equalsIgnoreCase("thursday")) {
            hariIni = "Kamis";
        } else if (hariIni.equalsIgnoreCase("friday")) {
            hariIni = "Jumat";
        } else if (hariIni.equalsIgnoreCase("saturday")) {
            hariIni = "Sabtu";
        }

        getToday();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView.setSelectedItemId(R.id.akun);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.videox:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_videox.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivitySlide.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_info.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.akun:

                        return true;
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



        tvDetik = findViewById(R.id.tvdetik);
        tvCNN = findViewById(R.id.tvcnn);
        tvKompas = findViewById(R.id.tvkompas);
        tvLiputan = findViewById(R.id.tvliputan6);
        tvRepublika = findViewById(R.id.tvrepublika);
        tvMI = findViewById(R.id.tvmediai);
        tvOkezone = findViewById(R.id.tvokezone);
        tvSindo = findViewById(R.id.tvsindonews);
        tvTribun = findViewById(R.id.tvtribun);
        tvAntara = findViewById(R.id.tvantara);
        tvJakartaPos = findViewById(R.id.tvJakartaPos);
        tvGoal = findViewById(R.id.tvGoal);
        tvBolacom = findViewById(R.id.tvBolaCom);
        tvBolanet = findViewById(R.id.tvBolanet);
        tvSuara = findViewById(R.id.tvMusic);
        tvTempo = findViewById(R.id.tvTempo);
        tvOto = findViewById(R.id.tvOtocom);
        tvOtosia = findViewById(R.id.tvotosia);
        tvviva = findViewById(R.id.tvviva);
        tvJpnn = findViewById(R.id.tvJpnn);
        stAntara = findViewById(R.id.stAntara);
        stGlobal = findViewById(R.id.stGlobalTV);
        stIndosiar = findViewById(R.id.stIndosiar);
        stInews = findViewById(R.id.stInews);
        stKompas = findViewById(R.id.stKompastv);
        stMetro = findViewById(R.id.stMetro);
        stMNC = findViewById(R.id.stMNCTV);
        stNet = findViewById(R.id.stNet);
        stPrambors = findViewById(R.id.stPrambors);
        stRcti = findViewById(R.id.stRCTI);
        stRTV = findViewById(R.id.stRTV);
        stSCTV = findViewById(R.id.stSctv);
        stTrans = findViewById(R.id.stTrans);
        stTrans7 = findViewById(R.id.stTrans7);
        stTvone = findViewById(R.id.stTvOne);
        stTVRI = findViewById(R.id.stTVRI);





        tvDetik.setOnClickListener(v -> {
                Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.detik.com/";
            i.putExtra("Value",urlx);
                startActivity(i);

        });

        tvTribun.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.tribunnews.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvSindo.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.sindonews.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvOkezone.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.okezone.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvMI.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.mediaindonesia.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvRepublika.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.republika.co.id/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvLiputan.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.liputan6.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvKompas.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.kompas.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvCNN.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.cnnindonesia.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvAntara.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.antaranews.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvJpnn.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.jpnn.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvviva.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.viva.co.id/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvOtosia.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.otosia.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvOto.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://www.oto.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvGoal.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://www.goal.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvBolanet.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.bola.net/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvBolacom.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.bola.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvSuara.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.suara.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvTempo.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://m.tempo.co/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });

        tvJakartaPos.setOnClickListener(v -> {
            Intent i;
            i = new Intent(getApplicationContext(), beritaUmum.class);
            urlx = "https://www.thejakartapost.com/";
            i.putExtra("Value",urlx);
            startActivity(i);

        });


        stTVRI.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/tvri"));

            startActivity(intent);
        });


        stTvone.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.tvonenews.com/live"));

            startActivity(intent);

        });


        stTrans7.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/trans7"));

            startActivity(intent);
        });
        stTrans.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/transtv"));

            startActivity(intent);
        });


        stSCTV.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.sctv.co.id/live-streaming"));

            startActivity(intent);

        });


        stRTV.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/rtv"));

            startActivity(intent);

        });


        stRcti.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.rcti.tv/livetv"));

            startActivity(intent);

        });



        stPrambors.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/pramborstv"));

            startActivity(intent);

        });


        stNet.setOnClickListener(v -> {
           Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/net"));

            startActivity(intent);


        });


        stMNC.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.inews.id/streaming/mnctv"));

            startActivity(intent);

        });


        stMetro.setOnClickListener(v -> {
               Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/metrotv"));

            startActivity(intent);


        });


        stKompas.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.kompas.tv/live"));

            startActivity(intent);

        });


        stInews.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.inews.id/streaming"));

            startActivity(intent);

        });


        stIndosiar.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://m.vidio.com/live/205-indosiar"));

            startActivity(intent);

        });


        stGlobal.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://tv.okezone.com/streaming/global_tv"));

            startActivity(intent);

        });


        stAntara.setOnClickListener(v -> {
             Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.useetv.com/livetv/antara"));

            startActivity(intent);

        });






    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + year;
        tvToday.setText(formatFix);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardHeadLine) {
            Intent intent = new Intent(MainActivityNews.this, HeadLineActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardSports) {
            Intent intent = new Intent(MainActivityNews.this, SportsNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardTechno) {
            Intent intent = new Intent(MainActivityNews.this, TechnologyActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardBusiness) {
            Intent intent = new Intent(MainActivityNews.this, BusinessActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardHealth) {
            Intent intent = new Intent(MainActivityNews.this, HealthActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cardEnter) {
            Intent intent = new Intent(MainActivityNews.this, EntertaimentActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();

    }
}