package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerviewOrderList;
    private Button btnOrderOrderList;
    private TextView txtHargaOrderList;
    private ImageView imgPrevOrderList;
    private ImageView imgNextOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        recyclerviewOrderList = findViewById(R.id.recyclerviewOrderList);
        btnOrderOrderList = findViewById(R.id.btnOrderOrderList);
        txtHargaOrderList = findViewById(R.id.txtHargaOrderList);
        imgPrevOrderList = findViewById(R.id.imgPrevOrderList);
        imgNextOrderList = findViewById(R.id.imgNextOrderList);

        imgPrevOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        imgNextOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

    }
}