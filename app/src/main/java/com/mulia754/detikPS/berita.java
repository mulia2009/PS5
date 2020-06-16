package com.mulia754.detikPS;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class berita extends AppCompatActivity {
    TextView tvView;
    String valurl;

     private InterstitialAd interstitial;

    public WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);


        tvView = (TextView) findViewById(R.id.textViewber);
        valurl = getIntent().getExtras().getString("Value");
        tvView.setText(valurl);
        //mengassign atau menentukan nilai url ke dalam bentuk editext string
        String urlx = tvView.getText().toString();
      myWebView = (WebView) findViewById(R.id.webviewx);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadUrl(urlx);
        myWebView.setWebViewClient(new MyBrowser());



        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("14C4478FF6BA8D617DA5D9FDA42472ED")
                .build();
        mAdView.loadAd(adRequest);
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
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        // disable going back to the MainActivityCorona

        super.onBackPressed();

    }

}
