package com.mulia754.detikPS.swipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter;
import com.mulia754.detikPS.berita;
import com.mulia754.detikPS.berita2;
import com.mulia754.detikPS.bottom.bottom_info;
import com.mulia754.detikPS.bottom.bottom_videox;
import com.mulia754.detikPS.buat.NewNoteActivity;
import com.mulia754.detikPS.buat.PasswordNewNote;
import com.mulia754.detikPS.indonews.MainActivityNews;
import com.mulia754.detikPS.kontakPS;
import com.mulia754.detikPS.database.model.Note;
import com.mulia754.detikPS.database.presenter.NotesPresenter;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class olahraga extends AppCompatActivity implements NotesView, NavigationView.OnNavigationItemSelectedListener {
    float x1,y1;
    float x2,y2;

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView gbrx;
    private TextView tvlink;

    private InterstitialAd interstitial;

    private long exitTime = 0;
    String url3 = " ";
    private RecyclerView mRvNotes;
    private NotesAdapter mNotesAdapter;
    private ArrayList<Note> mNotes;
    private NotesPresenter mNotesPresenter;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager;

    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "welcome_message";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";
    private static final String WELCOME_GAMBAR = "gambar";
    private static final String WELCOME_LINK = "link";

    String urlx = " ";
    public WebView myWebView;

    String uriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaolahraga);
        //recyxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
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
                }}
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "klik floating action button");
                Intent intent = new Intent(view.getContext(),
                        NewNoteActivity.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//xxxxxxxxxxxxxxx
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
                }
                return false;
            }
        });
        mWelcomeTextView = findViewById(R.id.welcomeTextView);
        gbrx = findViewById(R.id.gbrx);
        tvlink = findViewById(R.id.tvlink);
        Button fetchButton = findViewById(R.id.fetchButton);
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
        mRvNotes = (RecyclerView) findViewById(R.id.rv_note);
        mNotes = new ArrayList<Note>();
        mNotesAdapter = new NotesAdapter(this, mNotes);
        mNotesPresenter = new NotesPresenter(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes.setAdapter(mNotesAdapter);
        Collections.reverse(mNotes);
        mRvNotes.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager = new LinearLayoutManager(olahraga.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

// And now set it to the RecyclerView
        mRvNotes.setLayoutManager(mLayoutManager);
        mRvNotes.setAdapter(mNotesAdapter);
        mNotesPresenter.onCreate();
    }
    //public void displayInterstitial() {
    //   if (interstitial.isLoaded()) {
    //      interstitial.show();
    //  }
    // }
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
        myWebView = findViewById(R.id.webviewcover);
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
        WebView view = this.findViewById(R.id.webviewcover);  //sinkronisasi object berdasarkan id
        view.getSettings().setJavaScriptEnabled(true);  //untuk mengaktifkan javascript
        view.loadUrl(url);   //agar URL terload saat dibuka aplikasi
    }
    public void goToURL(View view) {
        TextView editText = findViewById(R.id.tvlink);
        Intent i;
        i = new Intent(getApplicationContext(), berita2.class);
        urlx = editText.getText().toString();
        i.putExtra("Value",urlx);
        startActivity(i);}
    @Override
    protected void onStart() {
        super.onStart();
        mNotesPresenter.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mNotesPresenter.onStop();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
else {
            super.onBackPressed();
            finish();
        }
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
            Intent intentku = new Intent(olahraga.this, PasswordNewNote.class);
            startActivity(intentku);
        }
        else if (id == R.id.nav_wa) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            uriString = "https://play.google.com/store/apps/details?id=com.mulia754.ps5\n" ;
            sharingIntent.putExtra(Intent.EXTRA_TEXT, uriString);
            sharingIntent.setPackage("com.whatsapp");
            startActivity(sharingIntent);
        }
        else if (id == R.id.nav_telegram) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            uriString ="https://play.google.com/store/apps/details?id=com.mulia754.ps5" +
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, uriString);
            sharingIntent.setPackage("org.telegram.messenger");
            startActivity(sharingIntent);
        }
        else if (id == R.id.nav_kontak) {
            Intent intentku = new Intent(olahraga.this, kontakPS.class);
            startActivity(intentku);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onComposeAction(MenuItem toolItem) {
        // pengecekannya di sini
        int id = toolItem.getItemId();

        if (id == R.id.miCompose) {
            Intent intentku = new Intent(olahraga.this, olahraga.class);
            startActivity(intentku);
        }else  if (id == R.id.searchItem) {
            Intent intentku2 = new Intent();
            intentku2.setAction(Intent.ACTION_VIEW);
            intentku2.addCategory(Intent.CATEGORY_BROWSABLE);
            intentku2.setData(Uri.parse("https://instagram.com/akmil2009?igshid=169peik1lstf7"));
            intentku2.setPackage("com.instagram.android");
            startActivity(intentku2);
        }
    }
    @Override
    public void refreshNoteList(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        mNotesAdapter.notifyDataSetChanged();

        /*perintah klik recyclerview*/
        mRvNotes.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    i = new Intent(getApplicationContext(), berita.class);
                    url3 = mNotes.get(position).getCover().toString();
                    i.putExtra("Value",url3);
                    startActivity(i);
                }



                char angle = getAngle(x1, y1, x2, y2);

                switch (e.getAction())
                {
                    // when user first touches the screen we get x and y coordinate
                    case MotionEvent.ACTION_DOWN:
                    {
                        x1 = e.getX();
                        y1 = e.getY();
                        if (inRange(angle, 0,30) || inRange(angle, 320, 360))

                        {
                            Intent i = new Intent(olahraga.this, teknologi.class);

                            startActivity(i);
                            overridePendingTransition(0,0);
                        }
                        // if right to left sweep event on screen

                    }
                    case MotionEvent.ACTION_UP:
                    {
                        x2 = e.getX();
                        y2 = e.getY();
                        //if left to right sweep event on screen
                        if (inRange(angle, 0,30) || inRange(angle, 320, 360))

                        {
                            Intent i = new Intent(olahraga.this, teknologi.class);

                            startActivity(i);
                            overridePendingTransition(0,0);
                        }
                        // if right to left sweep event on screen
                       

                    }
                }







                return false;
            }


            private boolean inRange(double angle, float init, float end){
                return (angle >= init) && (angle < end);
            }

            private char getAngle(float x1, float y1, float x2, float y2) {

                double rad = Math.atan2(y1-y2,x2-x1) + Math.PI;
                return (char) ((rad*180/Math.PI + 180)%360);
            }

            @Override
            public void onTouchEvent( RecyclerView rv,  MotionEvent e) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        })
        ;
    }
    //declare method for detecting gesture

}
