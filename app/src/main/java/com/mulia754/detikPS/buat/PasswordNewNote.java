package com.mulia754.detikPS.buat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.mulia754.detikPS.BuildConfig;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.bottom.bottom_videox;

import java.text.BreakIterator;

public class PasswordNewNote extends AppCompatActivity {
    private static final String TAG = bottom_videox.class.getSimpleName();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelcomeTextView;
    private TextView gbrx;
    private TextView tvlinkx;
    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "keterangan";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";
    private static final String WELCOME_GAMBAR = "logo";
    private static final String PASSWORDFIREBASE = "passwordfirebase";


    String urlx = " ";
    public WebView myWebView;

    String uriString;



    EditText  password;
    Button btnLogin;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_new_note);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        Button ButtonHub = (Button)findViewById(R.id.btnHub);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(5000);
         password = (EditText) findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);


        ButtonHub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=628114982009&text= Assalamualaikum..."));
                startActivity(intent);
            }
        });






        mWelcomeTextView = (TextView) findViewById(R.id.tvPassword);
        gbrx = (TextView) findViewById(R.id.gbrx);
        tvlinkx = (TextView) findViewById(R.id.tvlink);

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
                            Toast.makeText(PasswordNewNote.this, "Periksa koneksi jaringan",
                                    Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();



                    }
                });
        // [END fetch_config_with_callback]
    }


    private void displayWelcomeMessage() {
        String welcomeMessage = mFirebaseRemoteConfig.getString(PASSWORDFIREBASE);
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



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordKeyFirebase = mWelcomeTextView.getText().toString();
                // String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (passwordKey.equals(passwordKeyFirebase)){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PasswordNewNote.this, buatBerita.class);
                    startActivity(intent);
                    finish();
                }else {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(PasswordNewNote.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }

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