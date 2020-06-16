package com.mulia754.detikPS.buat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.database.presenter.NotePresenter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;
import java.util.Random;

public class NewNoteActivity extends AppCompatActivity implements
        View.OnClickListener {
    private static final String TAG = NewNoteActivity.class.getSimpleName();
    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    private NotePresenter mNotePresenter;
    Spinner spinner;
    String nama;
    TextView tv_siswa;

    Button btn_submit;
    Button button;
    TextView editText;
    Context context;
    private StorageReference mStorageRef;
    ProgressDialog mProgressDialog;

    Button btnDatePicker, btnTimePicker,btnHariPicker,btnOK;
    TextView txtDate, txtTime,txtHari;
    private int mYear, mMonth, mDay, mHour, mMinute;

    String txtDatex,txtTimex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        spinner = (Spinner) findViewById( R.id.spinner);
        tv_siswa = (TextView) findViewById(R.id.tvsiswa);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        // init constraintLayout
        constraintLayout = (RelativeLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(1000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(1000);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nama = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nama.equals("")){
                    tv_siswa.setText(nama);
                   }
            }
        });

//waktuuuuuuuuuuuuuuuuuuuu
        btnDatePicker=(Button)findViewById(R.id.btnPilihTanggal);
        btnTimePicker=(Button)findViewById(R.id.btnPilihJam);

        txtDate=(TextView)findViewById(R.id.tvJam);
        txtTime=(TextView)findViewById(R.id.tvTanggal);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
//zzzzzzzzzzzzzzzzzzzzzzzzzzz
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mProgressDialog=new ProgressDialog(NewNoteActivity.this);

        button = findViewById(R.id.ButtonChooseImage);
        editText = findViewById(R.id.et_note_title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(NewNoteActivity.this);

            }
        });





        mNotePresenter = new NotePresenter();

        final EditText etNoteTitle = (EditText) findViewById(R.id.et_note_title);
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
        Button btnOKwaktu = (Button) findViewById(R.id.btnOkWaktu);
        btnOKwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waktuAsli = txtTime.getText().toString();
                String tanggalAsli = txtDate.getText().toString();

                tv_siswa.setText(nama);
               etNoteWaktu.setText( nama + " ,  " + tanggalAsli+",   "+ waktuAsli + "   WIB");


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

                final StorageReference filepath=mStorageRef.child("Imagesnew").child(random()+".jpg");
                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String Durinew=taskSnapshot.getUploadSessionUri().toString();
                        Log.e("durinew",Durinew);
                        String Uuri=taskSnapshot.getStorage().child("Imagesnew").getDownloadUrl().toString();


                        //if getDownload is not working
                        //Get download Url
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri uris=uri;
                                Log.e("uurinew",uri.toString());
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
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            //txtTime.setText(hourOfDay + ":" + minute);
                            txtTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();




        }




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