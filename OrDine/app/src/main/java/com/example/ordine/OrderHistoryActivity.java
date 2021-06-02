package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerviewOrderHistory;
    private ImageView imgPrevOrderHistory;
    private ImageView imgNextOrderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerviewOrderHistory = findViewById(R.id.recyclerviewOrderHistory);
        imgPrevOrderHistory = findViewById(R.id.imgPrevOrderHistory);
        imgNextOrderHistory = findViewById(R.id.imgNextOrderHistory);

        imgPrevOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        imgNextOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }
}