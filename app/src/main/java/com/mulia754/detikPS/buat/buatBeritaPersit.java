package com.mulia754.detikPS.buat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mulia754.detikPS.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class buatBeritaPersit extends AppCompatActivity {

    Button loadimageBtn, uploadimageBtn;
    ImageView imageView;
    public static final int IMAGE_CODE = 1;
    private StorageReference mStorageRef;
    Uri imageUri;
    EditText edtxxxxx;
    FloatingActionButton floatingActionButton;


    DatabaseReference databaseReference;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.buatberitapersit);

        edtxxxxx = findViewById(R.id.edtZzz);
        loadimageBtn = findViewById(R.id.loadimagebtnId);
        uploadimageBtn = findViewById(R.id.uploadimagebtnId);
        imageView = findViewById(R.id.imageViewId);
        floatingActionButton=findViewById(R.id.floatingActionButtonId);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("image");
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://detik-ps.appspot.com");

        loadimageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_CODE);


            }
        });





        uploadimageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StorageReference mref = mStorageRef.child("image").child(String.valueOf(System.currentTimeMillis()));
                 mref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(buatBeritaPersit.this, "Done", Toast.LENGTH_SHORT).show();


                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;

                        Uri downloadUri = uriTask.getResult();
                        String mainUri= downloadUri.toString();

                        String id=   databaseReference.push().getKey();

                        Map<String , Object> imageMap= new HashMap<>();
                        imageMap.put("imageuri", mainUri);
                        edtxxxxx.setText(  mainUri );


                        databaseReference.child(id).setValue(imageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(buatBeritaPersit.this, "Data link success", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });





                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();
            //  imageView.setImageURI(imageUri);
            Picasso.get().load(imageUri).into(imageView);

        }


    }
}
