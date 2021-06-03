package com.example.ordine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import Model.OrderHistory;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerviewOrderHistory;
    private ImageView imgPrevOrderHistory;
    private ImageView imgNextOrderHistory;
    private OrderHistoryRVAdapter adapter;
    private FirebaseAuth auth;
    private String uid;
    private ArrayList<OrderHistory> listOrderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerviewOrderHistory = findViewById(R.id.recyclerviewOrderHistory);
        imgPrevOrderHistory = findViewById(R.id.imgPrevOrderHistory);
        imgNextOrderHistory = findViewById(R.id.imgNextOrderHistory);
        adapter = new OrderHistoryRVAdapter(listOrderHistory, getApplicationContext());
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

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

        setupRecyclerView();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("order history").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                listOrderHistory.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    OrderHistory orderHistory = new OrderHistory();
                    orderHistory.setNama(snapshot.child("nama").getValue().toString());
                    orderHistory.setImageurl(snapshot.child("image path").getValue().toString());
                    orderHistory.setHarga(snapshot.child("harga").getValue().toString());
                    listOrderHistory.add(orderHistory);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerviewOrderHistory.setLayoutManager(manager);
        recyclerviewOrderHistory.setAdapter(adapter);
    }

}