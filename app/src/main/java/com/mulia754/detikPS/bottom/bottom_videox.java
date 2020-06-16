package com.mulia754.detikPS.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.mulia754.detikPS.BuildConfig;
import com.mulia754.detikPS.MainActivity;
import com.mulia754.detikPS.berita_Video;
import com.mulia754.detikPS.buat.NewNoteActivity;
import com.mulia754.detikPS.buat.PasswordNewNote;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter3;

import com.mulia754.detikPS.indonews.MainActivityNews;
import com.mulia754.detikPS.database.model.Note3;
import com.mulia754.detikPS.buat.newCatatan;
import com.mulia754.detikPS.database.presenter.NotessPresenter3;
import com.mulia754.detikPS.kontakPS;
import com.mulia754.detikPS.swipe.MainActivitySlide;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bottom_videox extends AppCompatActivity implements NotesView3, NavigationView.OnNavigationItemSelectedListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private static final String TAG = bottom_videox.class.getSimpleName();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView gbrx;
    private TextView tvlink;

    private InterstitialAd interstitial;
    String url3 = " ";
    private RecyclerView mRvNotes3;
    private NotesAdapter3 mNotesAdapter3;
    private ArrayList<Note3> mNotes3;
    private NotessPresenter3 mNotesPresenter3;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager3;

    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "keterangan";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";
    private static final String WELCOME_GAMBAR = "logo";
    private static final String WELCOME_LINK = "linkLogo";

    String urlx = " ";
    public WebView myWebView;

    String uriString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_videox);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "klik floating action button");
                Intent intent = new Intent(view.getContext(),
                        NewNoteActivity.class);
                startActivity(intent);
            }
        });




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

//xxxxxxxxxxxxxxx
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.videox);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.videox:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivitySlide.class));
                        overridePendingTransition(0,0);
                        finish(); return true;


                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                bottom_info.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

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

        mWelcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        gbrx = (TextView) findViewById(R.id.gbrx);
        tvlink = (TextView) findViewById(R.id.tvlink);

        Button fetchButton = (Button) findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWelcome();
            }
        });

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        fetchWelcome();

        FirebaseApp.initializeApp(this);

        mRvNotes3 = (RecyclerView) findViewById(R.id.rv_note);


        mNotes3 = new ArrayList<Note3>();
        mNotesAdapter3 = new NotesAdapter3(this, mNotes3);
        mNotesPresenter3 = new NotessPresenter3(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes3.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes3.setAdapter(mNotesAdapter3);
        Collections.reverse(mNotes3);
        mRvNotes3.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager3 = new LinearLayoutManager(bottom_videox.this);
        mLayoutManager3.setReverseLayout(true);
        mLayoutManager3.setStackFromEnd(true);

// And now set it to the RecyclerView


        mRvNotes3.setLayoutManager(mLayoutManager3);
        mRvNotes3.setAdapter(mNotesAdapter3);

        mNotesPresenter3.onCreate();




    }

    private void fetchWelcome() {
        mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));

        long cacheExpiration = 3600; // 1 hour in seconds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(bottom_videox.this, "Periksa koneksi jaringan",
                                    Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();
                        displayGambar();
                        displayLink();

                    }
                });
        // [END fetch_config_with_callback]
    }


    private void displayWelcomeMessage() {
        String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);
        // [END get_config_values]
        if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
            mWelcomeTextView.setAllCaps(true);
        } else {
            mWelcomeTextView.setAllCaps(false);
        }
        mWelcomeTextView.setText(welcomeMessage);
        String passxx =  mWelcomeTextView.getText().toString();

        String url = passxx ;  //Pendefinisian URL
        myWebView = (WebView) findViewById(R.id.webviewcover);
//menseting tampilan url ke dalam tampilan webview
        myWebView.setWebViewClient(new WebViewClient());
//mengaktifkan javascript (secara default disable)


        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //method untuk mengaktifkan java script

    }


    private void displayLink() {
        String link = mFirebaseRemoteConfig.getString(WELCOME_LINK);
        // [END get_config_values]
        if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
            mWelcomeTextView.setAllCaps(true);
        } else {
            mWelcomeTextView.setAllCaps(false);
        }
        tvlink.setText(link);
        String passxx =  tvlink.getText().toString();

        String urlzzz = passxx ;  //Pendefinisian URL


    }


    private void displayGambar() {
        String gambar = mFirebaseRemoteConfig.getString(WELCOME_GAMBAR);
        // [END get_config_values]
        if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
            gbrx.setAllCaps(true);
        } else {
            gbrx.setAllCaps(false);
        }

        gbrx.setText(gambar);
        String gbrxx =  gbrx.getText().toString();

        String url = gbrxx ;  //Pendefinisian URL
        WebView view = (WebView) this.findViewById(R.id.webviewcover);  //sinkronisasi object berdasarkan id
        view.getSettings().setJavaScriptEnabled(true);  //untuk mengaktifkan javascript
        view.loadUrl(url);   //agar URL terload saat dibuka aplikasi




    }
    public void goToURL(View view) {

        TextView editText = (TextView) findViewById(R.id.tvlink);
        Intent i;
        i = new Intent(getApplicationContext(), bottom_videox.class);
        urlx = editText.getText().toString();
        i.putExtra("Value",urlx);
        startActivity(i);}



    @Override
    protected void onStart() {
        super.onStart();
        mNotesPresenter3.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mNotesPresenter3.onStop();

    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //Set SearchView dengan search manager
        return true;





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_buatBerita) {
            Intent intentku = new Intent(bottom_videox.this, PasswordNewNote.class);

            startActivity(intentku);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onComposeAction(MenuItem toolItem) {
        // pengecekannya di sini
        int id = toolItem.getItemId();

        if (id == R.id.miCompose) {
            Intent intentku = new Intent(bottom_videox.this, newCatatan.class);

            startActivity(intentku);
        }else  if (id == R.id.searchItem) {
            Intent intentku3 = new Intent();
            intentku3.setAction(Intent.ACTION_VIEW);
            intentku3.addCategory(Intent.CATEGORY_BROWSABLE);
            intentku3.setData(Uri.parse("https://instagram.com/akmil2009?igshid=169peik1lstf7"));
            intentku3.setPackage("com.instagram.android");
            startActivity(intentku3);
        }
    }


    @Override
    public void refreshNoteList3(List<Note3> notes3) {
        mNotes3.clear();
        mNotes3.addAll(notes3);
        mNotesAdapter3.notifyDataSetChanged();

        /*perintah klik recyclerview*/
        mRvNotes3.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(child);
                    Intent i;
                    i = new Intent(getApplicationContext(), berita_Video.class);
                    url3 = mNotes3.get(position).getCover3().toString();
                    i.putExtra("Value",url3);
                    startActivity(i);

                }


                return false;
            }



            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        })
        ;
    }



}
