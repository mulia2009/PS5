package com.mulia754.detikPS.buat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.presenter.NotePresenter7;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;

public class buatBeritaPolitik extends AppCompatActivity {
    private static final String TAG = buatBeritaPolitik.class.getSimpleName();

    private NotePresenter7 mNotePresenter7;
    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    Button button;
    TextView editText;
    Context context;
    private StorageReference mStorageRef7;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buatberitapolitik);
        // init constraintLayout
        constraintLayout = (RelativeLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(1000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(1000);
        mStorageRef7 = FirebaseStorage.getInstance().getReference();
        mProgressDialog=new ProgressDialog( buatBeritaPolitik.this);

        button = findViewById(R.id.ButtonChooseImage);
        editText = findViewById(R.id.et_note_title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start( buatBeritaPolitik.this);

            }
        });
        mNotePresenter7 = new NotePresenter7();

        final TextView etNoteTitle = (TextView) findViewById(R.id.et_note_title);
        final EditText etNoteContent = (EditText) findViewById(R.id.et_note_content);
        final EditText etNoteWaktu = (EditText) findViewById(R.id.et_note_waktu);
        final EditText etNoteCover = (EditText) findViewById(R.id.etNoteCover);

        Button btnNoteSave = (Button) findViewById(R.id.btn_note_submit);

        btnNoteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title7 = etNoteTitle.getText().toString();
                String content7 = etNoteContent.getText().toString();
                String waktu7 = etNoteWaktu.getText().toString();
                String cover7 = etNoteCover.getText().toString();

                mNotePresenter7.create(title7,content7,waktu7,cover7);

                onBackPressed();
            }
        });


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Log.e("Namevideo",resultUri.toString());
                mProgressDialog.setTitle("Uploading Image....");
                mProgressDialog.setMessage("Please Wait....");
                mProgressDialog.show();
                mProgressDialog.setCanceledOnTouchOutside(false);

                final StorageReference filepath=mStorageRef7.child("Imagesvideo").child(random()+".jpg");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String Durivideo=taskSnapshot.getUploadSessionUri().toString();
                        Log.e("durivideo",Durivideo);
                        String Uuri=taskSnapshot.getStorage().child("Imagesvideo").getDownloadUrl().toString();


                        //if getDownload is not working
                        //Get download Url
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri uris=uri;
                                Log.e("uurivideo",uri.toString());
                                editText.setText(uri.toString());

                            }
                        });


                        mProgressDialog.dismiss();


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
