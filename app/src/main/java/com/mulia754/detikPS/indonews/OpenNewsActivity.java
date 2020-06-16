package com.mulia754.detikPS.indonews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.buat.buatBerita;
import com.mulia754.detikPS.buat.newCatatan;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class OpenNewsActivity extends AppCompatActivity {
private FloatingActionButton fabShare;
    private ProgressBar mProgressBar;
    private WebView mWebView;

    String url = "https://www.google.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //android theme
        setContentView(R.layout.activity_open_news);

        //android toolbar
        fabShare= findViewById(R.id.fabShare);

        fabShare.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(Intent.createChooser(share, "Bagikan ke : "));
        });
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);
        url =  getIntent().getStringExtra("url");

        mWebView = findViewById(R.id.read_news);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mProgressBar.setProgress(0);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                mProgressBar.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                //mProgressBar.setProgress(0);
                url = urlStart;
                invalidateOptionsMenu();

            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                mProgressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open_news, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {//share news
            shareUrl(url);
        }
        return super.onOptionsItemSelected(item);
    }

    //back pressed
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //setup toolbar


    //share news
    private void shareUrl(String url) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(share, "Bagikan ke : "));
    }

}
