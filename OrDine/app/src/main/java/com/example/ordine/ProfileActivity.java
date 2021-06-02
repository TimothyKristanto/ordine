package com.example.ordine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.User;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgPrevProfile, imgNextProfile, imgLogoutProfile;
    private TextView txtNamaProfile, txtEmailProfile, txtTableNumberProfile;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgPrevProfile = findViewById(R.id.imgPrevProfile);
        imgLogoutProfile = findViewById(R.id.imgLogoutProfile);
        imgNextProfile = findViewById(R.id.imgNextProfile);
        txtNamaProfile = findViewById(R.id.txtNamaProfile);
        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        txtTableNumberProfile = findViewById(R.id.txtTableNumberProfile);
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        Intent intent = getIntent();

        String nama = intent.getStringExtra("namaUser");
        String email = intent.getStringExtra("emailUser");
        String tableNum = intent.getStringExtra("tableNumUser");

        txtNamaProfile.setText(nama);
        txtEmailProfile.setText(email);
        txtTableNumberProfile.setText(tableNum);

        imgLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgPrevProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        imgNextProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}