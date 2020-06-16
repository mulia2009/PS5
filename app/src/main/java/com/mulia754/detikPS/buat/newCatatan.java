package com.mulia754.detikPS.buat;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.mulia754.detikPS.database.presenter.NotePresenter2;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class newCatatan extends AppCompatActivity {
    private static final String TAG = newCatatan.class.getSimpleName();

    private NotePresenter2 mNotePresenter2;
    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    Button button;
    TextView editText;
    Context context;
    private StorageReference mStorageRef2;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_catatan);
        // init constraintLayout
        constraintLayout = (RelativeLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(1000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(1000);
        mStorageRef2 = FirebaseStorage.getInstance().getReference();
        mProgressDialog=new ProgressDialog(newCatatan.this);

        button = findViewById(R.id.ButtonChooseImage);
        editText = findViewById(R.id.et_note_title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(newCatatan.this);

            }
        });
        mNotePresenter2 = new NotePresenter2();

        final TextView etNoteTitle = (TextView) findViewById(R.id.et_note_title);
        final EditText etNoteContent = (EditText) findViewById(R.id.et_note_content);
        final EditText etNoteWaktu = (EditText) findViewById(R.id.et_note_waktu);
        final EditText etNoteCover = (EditText) findViewById(R.id.etNoteCover);

        Button btnNoteSave = (Button) findViewById(R.id.btn_note_submit);

        btnNoteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title2 = etNoteTitle.getText().toString();
                String content2 = etNoteContent.getText().toString();
                String waktu2 = etNoteWaktu.getText().toString();
                String cover2 = etNoteCover.getText().toString();

                mNotePresenter2.create(title2,content2,waktu2,cover2);

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
                Log.e("Name",resultUri.toString());
                mProgressDialog.setTitle("Uploading Image....");
                mProgressDialog.setMessage("Please Wait....");
                mProgressDialog.show();
                mProgressDialog.setCanceledOnTouchOutside(false);

                final StorageReference filepath=mStorageRef2.child("Imagesweb").child(random()+".jpg");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String Duriweb=taskSnapshot.getUploadSessionUri().toString();
                        Log.e("duriweb",Duriweb);
                        String Uuri=taskSnapshot.getStorage().child("Imagesweb").getDownloadUrl().toString();


                        //if getDownload is not working
                        //Get download Url
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri uris=uri;
                                Log.e("uuriweb",uri.toString());
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