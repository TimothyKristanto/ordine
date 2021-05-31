package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private ImageView imgPrevMenu;
    private ImageView imgNextMenu;
    private RecyclerView recyclerviewMenu;
    private Button btnAddMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgPrevMenu = findViewById(R.id.imgPrevMenu);
        imgNextMenu = findViewById(R.id.imgNextMenu);
        recyclerviewMenu = findViewById(R.id.recyclerviewMenu);
        btnAddMenu = findViewById(R.id.btnAddMenu);

        imgPrevMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        imgNextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
                startActivity(intent);
            }
        });

    }
}