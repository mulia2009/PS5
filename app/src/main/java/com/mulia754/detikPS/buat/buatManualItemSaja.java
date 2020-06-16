package com.mulia754.detikPS.buat;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mulia754.detikPS.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mulia754.detikPS.database.presenter.NotePresenter;

public class buatManualItemSaja extends AppCompatActivity {
    private static final String TAG = NewNoteActivity.class.getSimpleName();
    private String KEY_NAME = "GAMBARBOS";
    private NotePresenter mNotePresenter;
    String gambarBos;
    Button button,btnLanjut;
    Button buttonimgHalaman;

    TextView editText,txtHalamanBerita;

    Context context;
    private StorageReference mStorageRef;
    ProgressDialog mProgressDialog;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_manual_item_saja);
        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mProgressDialog=new ProgressDialog(buatManualItemSaja.this);


        buttonimgHalaman = findViewById(R.id.btniGambarBerita);

        button = findViewById(R.id.ButtonChooseImage);
        btnLanjut = findViewById(R.id.btnLanjut);

        editText = findViewById(R.id.et_note_title);
        txtHalamanBerita = findViewById(R.id.txtGambarHalamanBerita);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(buatManualItemSaja.this);

            }
        });


        buttonimgHalaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(buatManualItemSaja.this);

            }
        });


        mNotePresenter = new NotePresenter();

        final TextView etNoteTitle = (TextView) findViewById(R.id.et_note_title);
        final EditText etNoteContent = (EditText) findViewById(R.id.et_note_content);
        final EditText etNoteWaktu = (EditText) findViewById(R.id.et_note_waktu);
        final EditText etNoteCover = (EditText) findViewById(R.id.etNoteCover);

        Button btnNoteSave = (Button) findViewById(R.id.btn_note_submit);

        btnNoteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etNoteTitle.getText().toString();
                String content = etNoteContent.getText().toString();
                String waktu = etNoteWaktu.getText().toString();
                String cover = etNoteCover.getText().toString();

                mNotePresenter.create(title,content,waktu,cover);

                onBackPressed();
            }
        });


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gambarBos = editText.getText().toString();


                Intent i = new Intent(buatManualItemSaja.this,buatManual.class);

                if(gambarBos.equals("") ){
                      }else{
                    i.putExtra(KEY_NAME,gambarBos);

                    startActivity(i);
                }


            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Log.e("Name",resultUri.toString());
                mProgressDialog.setTitle("Uploading Image....");
                mProgressDialog.setMessage("Please Wait....");
                mProgressDialog.show();
                mProgressDialog.setCanceledOnTouchOutside(false);

                final StorageReference filepath=mStorageRef.child("Imagesberita2").child(random()+".jpg");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String Duriberita2=taskSnapshot.getUploadSessionUri().toString();
                        Log.e("duriberita2",Duriberita2);
                        String Uuri=taskSnapshot.getStorage().child("Imagesberita2").getDownloadUrl().toString();


                        //if getDownload is not working
                        //Get download Url
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri uris=uri;
                                Log.e("uuriberita2",uri.toString());
                                editText.setText(uri.toString());
                                gambarBos = editText.getText().toString();
                            }
                        });


                        mProgressDialog.dismiss();

                        Intent i = new Intent(buatManualItemSaja.this,buatManual.class);

                        }

                });
           } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
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
