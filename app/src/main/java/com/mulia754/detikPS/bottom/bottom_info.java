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
import android.widget.ImageView;
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
import com.mulia754.detikPS.buat.NewNoteActivity;
import com.mulia754.detikPS.buat.PasswordNewNote;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.adapter.NotesAdapter2;
import com.mulia754.detikPS.halamanWebsite;

import com.mulia754.detikPS.indonews.MainActivityNews;
import com.mulia754.detikPS.database.model.Note2;
import com.mulia754.detikPS.buat.newCatatan;
import com.mulia754.detikPS.database.presenter.NotessPresenter2;
import com.mulia754.detikPS.kontakPS;
import com.mulia754.detikPS.swipe.MainActivitySlide;
import com.mulia754.detikPS.util.MarginItemDecoration;
import com.mulia754.detikPS.database.view.NotesView2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bottom_info extends AppCompatActivity implements NotesView2, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = bottom_info.class.getSimpleName();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView gbrx;
    private TextView tvlink;


    String url3 = " ";
    private RecyclerView mRvNotes2;
    private NotesAdapter2 mNotesAdapter2;
    private ArrayList<Note2> mNotes2;
    private NotessPresenter2 mNotesPresenter2;
    // Declare your RecyclerView and the LinearLayoutManager like this
    private LinearLayoutManager mLayoutManager2;

    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "keterangan";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";
    private static final String WELCOME_GAMBAR = "logo";
    private static final String WELCOME_LINK = "linkLogo";

    String urlx = " ";
    public WebView myWebView;

    String uriString;


    private InterstitialAd interstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_info);

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


        AdView mAdView = findViewById(R.id.adView2);
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

        bottomNavigationView.setSelectedItemId(R.id.info);
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
                       return true;

                    case R.id.akun:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivityNews.class));
                        overridePendingTransition(0,0);
                        finish();
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

        mRvNotes2 = (RecyclerView) findViewById(R.id.rv_note);


        mNotes2 = new ArrayList<Note2>();
        mNotesAdapter2 = new NotesAdapter2(this, mNotes2);
        mNotesPresenter2 = new NotessPresenter2(this);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        mRvNotes2.addItemDecoration(new MarginItemDecoration(itemMargin));
        mRvNotes2.setAdapter(mNotesAdapter2);
        Collections.reverse(mNotes2);
        mRvNotes2.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager2 = new LinearLayoutManager(bottom_info.this);
        mLayoutManager2.setReverseLayout(true);
        mLayoutManager2.setStackFromEnd(true);

// And now set it to the RecyclerView


        mRvNotes2.setLayoutManager(mLayoutManager2);
        mRvNotes2.setAdapter(mNotesAdapter2);

        mNotesPresenter2.onCreate();



/*

        CardView cardKemhan = (CardView) findViewById(R.id.cardKEMHAN);

        cardKemhan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });


        CardView cardTNI = (CardView) findViewById(R.id.cardTNI);

        cardTNI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.tni.mil.id"));
                startActivity(intent);
            }
        });


        CardView cardTNIAD = (CardView) findViewById(R.id.cardTNIAD);

        cardTNIAD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.tniad.mil.id"));
                startActivity(intent);
            }
        });
        CardView cardTNIAL = (CardView) findViewById(R.id.cardTNIAL);

        cardTNIAL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.tnial.mil.id"));
                startActivity(intent);
            }
        });
        CardView cardTNIAU = (CardView) findViewById(R.id.cardTNIAU);

        cardTNIAU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.tni-au.mil.id"));
                startActivity(intent);
            }
        });




*/


///////////picasso
        String uriTni = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/tni.png?alt=media&token=d954f41a-7731-45a8-9375-ef890974965b";
        String uriKemhan = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kemhan.png?alt=media&token=bda2ddc1-9c61-4575-8098-9bb37ecbbc8b";
        String uriTNIAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/tniad.png?alt=media&token=bbae91ff-e39e-4ba2-b95e-dedfea8c4c73";
        String uriTNIAL =  "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/tnial.png?alt=media&token=a49f4b09-94f4-4d8a-9a48-a4a6a654f9bd";

        String uriTNIAU = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/tniau.png?alt=media&token=c84b6c5a-83bf-46f5-abc7-9d0dbed00b1d";
        String uriKODIKLAT = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodiklat.png?alt=media&token=b7c0380c-0b9f-4a87-8a47-55338800a60e";
        String uriKOPASSUS = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kopassus.png?alt=media&token=3a28be5e-44d5-4b7a-9955-4767f77ad333";
        String uriKOSTRAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kostrad.png?alt=media&token=63033f70-5e2b-47ee-a63d-5c6126311b39";
        String uriPUSSENIF = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/pussenif.png?alt=media&token=ab7aa5ce-9500-462c-940c-96ae8ad3cb9e";
        String uriPUSSENKAV = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kavaleri.png?alt=media&token=fea131e6-7499-48da-80da-822eec185fdc";
        String uriPUSSENARMED = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/DASD.png?alt=media&token=1cdf00dd-93b1-4e14-a5b5-10dd03fb2022";
        String uriPUSSENARHANUD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/arhanud.png?alt=media&token=e3a94a26-8f66-43fd-9519-958824f78204";
        String uriDIRZI = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/zeni.png?alt=media&token=fde27d14-44eb-4b05-90b6-1ef5c1008312";
        String uriPENERBAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/penerbad.png?alt=media&token=0228f63c-8435-430c-9c1a-2945f84b33b6";
        String uriPAL = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/pal.png?alt=media&token=3e4a7671-2ed5-4844-8565-095831d46bbc";
        String uriTOP = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/topografi.png?alt=media&token=e3045a41-8e2b-48b5-9d15-5516829c5f5a";
        String uriHUB = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/hubad.png?alt=media&token=3cdd8512-e162-4fca-9a63-8e71229ce27d";
        String uriPOM = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/pom.png?alt=media&token=cff03664-8ba6-426d-a246-60633566e384";
        String uriKESAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/puskesad.png?alt=media&token=def88a9d-3e54-42b6-ba7a-be010165ff56";
        String uriBEKANG = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/bekang.png?alt=media&token=f708faf2-fdca-415a-ae74-8517b3b3827c";
        String uriAJEN = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/ajen.png?alt=media&token=767c9c55-decf-4200-a0ac-7fbf337f9452";
        String uriKUAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/dirkuad.png?alt=media&token=bc01afdd-5d4f-4c34-99c8-bd65a2b7bd78";
        String uriKUMAD = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kumad.png?alt=media&token=69a0ac7b-9639-405b-8154-7708c219775b";
        String uriJAYA = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodamjaya.png?alt=media&token=cf48664c-2a28-4146-9647-b9139d4f7051";
        String uriIM = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/im.png?alt=media&token=b11c8933-8a06-4af7-92dd-0f502c5c5d44";
        String uri1 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam1.png?alt=media&token=df7111ec-d079-43e9-948e-1949351f952a";
        String uri2 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam2.png?alt=media&token=88b77872-4315-4e30-9a22-951cd36a8110";
        String uri3 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam%203.png?alt=media&token=9547afbd-fd81-49eb-9a34-e89701c1cb6b";
        String uri4 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam4.png?alt=media&token=6b23c770-ccc1-4457-a7e2-ddf974b7cd0b";
        String uri5 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam5.png?alt=media&token=58990051-66ce-4200-bb2d-b01cb2c2ff64";
        String uri6 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam6.png?alt=media&token=3590a558-d54c-456b-84e7-8a9129a39589";
        String uri9 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam9.png?alt=media&token=3524b524-0df4-4d7f-9566-e9fbbf358d9c";
        String uri12 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam12.png?alt=media&token=f52fd3d8-71e1-4430-9eba-d6003e1259f7";
        String uri13 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam13.png?alt=media&token=d714dfee-b932-4cb2-93b5-e96dc9bd2c1b";
        String uri14 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam14.png?alt=media&token=924ed783-4443-4ea8-94fc-24387d6786d0";
          String uri16 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam16.png?alt=media&token=8ed4e9a9-eb0c-432b-b41d-30561942b1ab";
        String uri17 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam17.png?alt=media&token=d239d694-4281-4a09-8f26-115d7a1675c7";
        String uri18 = "https://firebasestorage.googleapis.com/v0/b/detik-ps.appspot.com/o/kodam18.png?alt=media&token=2c94fd44-7589-4ffa-a375-b2143817dcd3";



        ImageView ivBasicImage = findViewById(R.id.imageViewKemhan);
        ImageView ivBasicImageTNI = findViewById(R.id.imageViewTNI);
        ImageView ivBasicTNIAD = findViewById(R.id.imageViewAD    );
        ImageView ivBasicTNIAL = findViewById(R.id.imageViewAL   );
        ImageView ivBasicTNIAU = findViewById(R.id.imageViewAU   );
        ImageView ivBasicKOPASSUS = findViewById(R.id.imageViewcardKopassus    );
        ImageView ivBasicKOSTRAD = findViewById(R.id.imageViewcardKostrad    );
        ImageView ivBasicPUSSENIF = findViewById(R.id.imageViewcardPussenif    );
        ImageView ivBasicPUSSENKAV = findViewById(R.id.imageViewcardPussenkav    );
        ImageView ivBasicPUSSENARMED = findViewById(R.id.imageViewcardPussenarmed    );
        ImageView ivBasicPUSSENARHANUD = findViewById(R.id.imageViewcardPussenArhanud    );
        ImageView ivBasicBEKANG = findViewById(R.id.imageViewcardPusBekangad    );
        ImageView ivBasicAJEN = findViewById(R.id.imageViewcardDitajenad    );
        ImageView ivBasicZENI = findViewById(R.id.imageViewcardDirziad    );
        ImageView ivBasicPOM = findViewById(R.id.imageViewcardPuspomad    );
        ImageView ivBasicTOP = findViewById(R.id.imageViewcardcardDirtopad    );
        ImageView ivBasicPAL = findViewById(R.id.imageViewKodiklaaat    );
        ImageView ivBasicHUB = findViewById(R.id.SSSSS    );
        ImageView ivBasicKESAD = findViewById(R.id.imageViewcardcardPuskesad    );
        ImageView ivBasicKUMAD = findViewById(R.id.imageViewcardPudsdssenif    );
        ImageView ivBasicKUAD = findViewById(R.id.aaaaa    );
        ImageView ivBasicPENERBAD = findViewById(R.id.imageViewcardPusPenerbad    );
        ImageView ivBasicJAYA = findViewById(R.id.cardKodam4xx    );
        ImageView ivBasicIM = findViewById(R.id.imageViewcardKodamIM    );
        ImageView ivBasic1 = findViewById(R.id.imageViewcardcardKodam1    );
        ImageView ivBasic2 = findViewById(R.id.imageViecardKodam2    );
        ImageView ivBasic3 = findViewById(R.id.cardKodam3a    );
        ImageView ivBasic4 = findViewById(R.id.cardKodam4a    );
        ImageView ivBasic5 = findViewById(R.id.cardKodam5a    );
        ImageView ivBasic6 = findViewById(R.id.cardKodam6d    );
        ImageView ivBasic9 = findViewById(R.id.cardKodam9QQ    );
        ImageView ivBasic12 = findViewById(R.id.cardKodam12ASD    );
        ImageView ivBasic13 = findViewById(R.id.cardKodam4acxz    );
        ImageView ivBasic14 = findViewById(R.id.cardKodam14w    );

        ImageView ivBasic16 = findViewById(R.id.cardKodam4s    );
        ImageView ivBasic17 = findViewById(R.id.cardKodam4sd    );
        ImageView ivBasic18 = findViewById(R.id.cardKodam4cxz    );
        ImageView ivBasicKODIKLAT = findViewById(R.id.imageViewKodiklat    );





        Picasso.get().load(uriTni).into(ivBasicImageTNI);
        Picasso.get().load(uriKemhan).into(ivBasicImage);
        Picasso.get().load(uriTNIAD   ).into(ivBasicTNIAD    );
        Picasso.get().load(uriTNIAL   ).into(ivBasicTNIAL    );
        Picasso.get().load(uriTNIAU   ).into(ivBasicTNIAU    );
        Picasso.get().load(uriKODIKLAT   ).into(ivBasicKODIKLAT    );
        Picasso.get().load(uriKOPASSUS   ).into(ivBasicKOPASSUS    );
        Picasso.get().load(uriKOSTRAD   ).into(ivBasicKOSTRAD    );
        Picasso.get().load(uriPUSSENIF   ).into(ivBasicPUSSENIF    );
        Picasso.get().load(uriPUSSENKAV   ).into(ivBasicPUSSENKAV    );
        Picasso.get().load(uriPUSSENARMED   ).into(ivBasicPUSSENARMED    );
        Picasso.get().load(uriPUSSENARHANUD   ).into(ivBasicPUSSENARHANUD    );
        Picasso.get().load(uriDIRZI   ).into(ivBasicZENI    );
        Picasso.get().load(uriPOM   ).into(ivBasicPOM    );
        Picasso.get().load(uriTOP   ).into(ivBasicTOP    );
        Picasso.get().load(uriKUAD   ).into(ivBasicKUAD    );
        Picasso.get().load(uriKUMAD   ).into(ivBasicKUMAD    );
        Picasso.get().load(uriKESAD   ).into(ivBasicKESAD    );
        Picasso.get().load(uriHUB   ).into(ivBasicHUB    );
        Picasso.get().load(uriBEKANG   ).into(ivBasicBEKANG    );
        Picasso.get().load(uriPENERBAD   ).into(ivBasicPENERBAD    );
        Picasso.get().load(uriPAL   ).into(ivBasicPAL    );
        Picasso.get().load(uriAJEN   ).into(ivBasicAJEN    );
        Picasso.get().load(uriJAYA   ).into(ivBasicJAYA    );
        Picasso.get().load(uriIM   ).into(ivBasicIM    );
        Picasso.get().load(uri1   ).into(ivBasic1    );
        Picasso.get().load(uri2   ).into(ivBasic2  );
        Picasso.get().load(uri3   ).into(ivBasic3    );
        Picasso.get().load(uri4   ).into(ivBasic4    );
        Picasso.get().load(uri5   ).into(ivBasic5    );
        Picasso.get().load(uri6   ).into(ivBasic6    );
        Picasso.get().load(uri9   ).into(ivBasic9    );
        Picasso.get().load(uri12   ).into(ivBasic12    );
        Picasso.get().load(uri13   ).into(ivBasic13    );
         Picasso.get().load(uri14   ).into(ivBasic14    );
        Picasso.get().load(uri16   ).into(ivBasic16    );
        Picasso.get().load(uri17  ).into(ivBasic17    );
        Picasso.get().load(uri18   ).into(ivBasic18    );






    }

    public void ButtonOnClick(View V) {

        int id = V.getId();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);


        if (id == R.id.cardKEMHAN) {
            intent.setData(Uri.parse("https://www.kemhan.go.id"));
            startActivity(intent);


        } else if (id == R.id.cardTNI) {
            intent.setData(Uri.parse("https://www.tni.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardTNIAD) {
            intent.setData(Uri.parse("https://www.tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardTNIAU) {
            intent.setData(Uri.parse("https://www.tni-au.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardTNIAL) {
            intent.setData(Uri.parse("https://www.tnial.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodiklat) {
            intent.setData(Uri.parse("https://kodiklat-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKopassus) {
            intent.setData(Uri.parse("https://www.kopassus.mil.id/"));
            startActivity(intent);

        }
        else if (id == R.id.cardKostrad) {
            intent.setData(Uri.parse("https://kostrad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPussenif) {
            intent.setData(Uri.parse("https://pussenif.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPussenkav) {
            intent.setData(Uri.parse("https://www.pussenkav.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPussenarmed) {
            intent.setData(Uri.parse("https://www.tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPussenArhanud) {
            intent.setData(Uri.parse("https://pussenarhanud.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardDirziad) {
            intent.setData(Uri.parse("https://www.ditzi-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPuspomad) {
            intent.setData(Uri.parse("https://www.puspomad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPusPenerbad) {
            intent.setData(Uri.parse("https://puspenerbad-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPusBekangad) {
            intent.setData(Uri.parse("https://www.ditbekangad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardDitajenad) {
            intent.setData(Uri.parse("https://www.ditajen-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardDirtopad) {
            intent.setData(Uri.parse("https://www.tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPuskesad) {
            intent.setData(Uri.parse("https://www.kesad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodamJaya) {
            intent.setData(Uri.parse("https://kodamjaya-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodamIM) {
            intent.setData(Uri.parse("https://www.kodamim-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam1) {
            intent.setData(Uri.parse("http://kodam1-bukitbarisan.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam2) {
            intent.setData(Uri.parse("http://www.kodam-ii-sriwijaya.mil.id/index.php?module=content&id=1"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam3) {
            intent.setData(Uri.parse("https://www.siliwangi.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam4) {
            intent.setData(Uri.parse("https://www.kodam4.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam5) {
            intent.setData(Uri.parse("https://tniad.mil.id/berita/satuan/kotama/kodam-v-brawijaya"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam6) {
            intent.setData(Uri.parse("https://www.kodam-mulawarman.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam9) {
            intent.setData(Uri.parse("https://kodam-udayana.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam12) {
            intent.setData(Uri.parse("https://kodamtanjungpura-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam14) {
            intent.setData(Uri.parse("http://kodam14hasanuddin-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam16) {
            intent.setData(Uri.parse("https://www.kodam16pattimura.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam17) {
            intent.setData(Uri.parse("https://kodam17cenderawasih-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam18) {
            intent.setData(Uri.parse("https://kasuari18-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardhUBAD) {
            intent.setData(Uri.parse("https://dithub-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardPalad) {
            intent.setData(Uri.parse("http://ditpal-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKumad) {
            intent.setData(Uri.parse("http://www.sthmahmpthm.ac.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardDitkuad) {
            intent.setData(Uri.parse("http://ditkuad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam14) {
            intent.setData(Uri.parse("https://www.kodam14hasanuddintniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardKodam13) {
            intent.setData(Uri.parse("https://www.kodam13-tniad.mil.id"));
            startActivity(intent);

        }
        else if (id == R.id.cardmAJALAHtNI) {
            intent.setData(Uri.parse("https://majalah.tni.mil.id"));
            startActivity(intent);

        }

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
                            Toast.makeText(bottom_info.this, "Periksa koneksi jaringan",
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



        //method untuk mengaktifkan java script

        ImageView imageHeader = findViewById(R.id.imageHeader);
        Picasso.get().load(url).into(imageHeader);

    }
    public void goToURL(View view) {

        TextView editText = findViewById(R.id.tvlink);
        Intent i;
        i = new Intent(getApplicationContext(), halamanWebsite.class);
        urlx = editText.getText().toString();
        i.putExtra("Value",urlx);
        startActivity(i);
        finish();
    }



    @Override
    protected void onStart() {
        super.onStart();
        mNotesPresenter2.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mNotesPresenter2.onStop();

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
            Intent intentku = new Intent(bottom_info.this, PasswordNewNote.class);

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
            Intent intentku = new Intent(bottom_info.this, newCatatan.class);

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
    public void refreshNoteList2(List<Note2> notes2) {
        mNotes2.clear();
        mNotes2.addAll(notes2);
        mNotesAdapter2.notifyDataSetChanged();

        /*perintah klik recyclerview*/
        mRvNotes2.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
                    i = new Intent(getApplicationContext(), halamanWebsite.class);
                    url3 = mNotes2.get(position).getCover2().toString();
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
