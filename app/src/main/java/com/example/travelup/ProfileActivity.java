package com.example.travelup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    //Declarations

    ImageView iv_profile;
    final int REGISTER_ACTIVITY = 1;
    private static final int PICK_IMAGE = 100;
    String fname = "",lname = "", number = "", city = "", email = "";
    Uri image_uri;
    TextView email_address, phone_number, street_address, first_name, last_name;



//    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email_address = findViewById(R.id.email_address);
        phone_number = findViewById(R.id.phone_number);
        street_address = findViewById(R.id.street_address);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);

        email_address.setText(email);
        phone_number.setText(number);
        street_address.setText(city);
        first_name.setText(fname);
        last_name.setText(lname);

        iv_profile = findViewById(R.id.iv_profile);

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


        //ActionBar

//        actionBar = getSupportActionBar();
//        actionBar.setTitle("Profile");
    }

    public void openGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {
            image_uri = data.getData();
            iv_profile.setImageURI(image_uri);
        }
        else if(resultCode == RESULT_OK && requestCode == REGISTER_ACTIVITY)
        {
                fname = data.getStringExtra("fname");
                lname = data.getStringExtra("lname");
                city = data.getStringExtra("city");
                number = data.getStringExtra("number");
                email = data.getStringExtra("email");

        }
        else{
            Toast.makeText(this, "Something is wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
