package com.mulia754.detikPS.corona.activities;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.corona.fragment.CountryFragment;
import com.mulia754.detikPS.corona.fragment.RingkasanFragment;
import com.mulia754.detikPS.corona.fragment.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public class MainActivityCorona extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView tvToday;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_corona);

        if (savedInstanceState == null) {
            RingkasanFragment ringkasanFragment = new RingkasanFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.flMain, ringkasanFragment)
                    .commit();
        }

        tvToday = findViewById(R.id.tvDate);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);



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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.summaryMenu:
                RingkasanFragment ringkasanFragment = new RingkasanFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, ringkasanFragment)
                        .commit();
                return true;

            case R.id.summaryIdnMenu:
                CountryFragment countryFragment = new CountryFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, countryFragment)
                        .commit();
                return true;

            case R.id.historyMenu:
                RiwayatFragment riwayatFragment = new RiwayatFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, riwayatFragment)
                        .commit();
                return true;
        }
        return false;
    }
}
