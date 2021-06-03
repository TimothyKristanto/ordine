package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MenuDetailsActivity extends AppCompatActivity {
    private TextView txtNamaMakananMenuDetails, txtHargaMenuDetails, txtGenreMenuDetails;
    private ImageView imgMakananMenuDetails, imgAddMenuDetails;
    private TextInputLayout txtInputJumlahMenuDetails;
    private FloatingActionButton actBtnBackMenuDetails;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        txtNamaMakananMenuDetails = findViewById(R.id.txtNamaMakananMenuDetails);
        txtHargaMenuDetails = findViewById(R.id.txtHargaMenuDetails);
        txtGenreMenuDetails = findViewById(R.id.txtGenreMenuDetails);
        imgMakananMenuDetails = findViewById(R.id.imgMakananMenuDetails);
        imgAddMenuDetails = findViewById(R.id.imgAddMenuDetails);
        txtInputJumlahMenuDetails = findViewById(R.id.txtInputJumlahMenuDetails);
        actBtnBackMenuDetails = findViewById(R.id.actBtnBackMenuDetails);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");
        map = new HashMap<>();
        String uid = auth.getCurrentUser().getUid();

        Intent intent = getIntent();
        String namaMakanan = intent.getStringExtra("namaMakanan");
        String genre = intent.getStringExtra("genre");
        String imagePath = intent.getStringExtra("imagePath");
        int harga = intent.getIntExtra("harga", 0);

        txtNamaMakananMenuDetails.setText(namaMakanan);
        txtHargaMenuDetails.setText("Rp " + String.valueOf(harga));
        txtGenreMenuDetails.setText(genre);
        Glide.with(getApplicationContext()).load(imagePath).into(imgMakananMenuDetails);

        actBtnBackMenuDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDetailsActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgAddMenuDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jumlah = txtInputJumlahMenuDetails.getEditText().getText().toString().trim();

                if(!TextUtils.isEmpty(String.valueOf(jumlah))) {
                    map.put("nama", namaMakanan);
                    map.put("harga", harga);
                    map.put("image path", imagePath);
                    map.put("jumlah", Integer.parseInt(jumlah));
                    database.getReference().child("order list").child(uid).child(namaMakanan).updateChildren(map);
                    Intent intent = new Intent(MenuDetailsActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MenuDetailsActivity.this, "Please fill in the order quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}