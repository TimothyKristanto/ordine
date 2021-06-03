package com.example.ordine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import Model.OrderList;

public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerviewOrderList;
    private Button btnOrderOrderList;
    private TextView txtHargaOrderList;
    private ImageView imgPrevOrderList;
    private ImageView imgNextOrderList;
    private ArrayList<OrderList> listOrder;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private OrderListRVAdapter adapter;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        recyclerviewOrderList = findViewById(R.id.recyclerviewOrderList);
        btnOrderOrderList = findViewById(R.id.btnOrderOrderList);
        txtHargaOrderList = findViewById(R.id.txtHargaOrderList);
        imgPrevOrderList = findViewById(R.id.imgPrevOrderList);
        imgNextOrderList = findViewById(R.id.imgNextOrderList);
        listOrder = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");
        adapter = new OrderListRVAdapter(listOrder, getApplicationContext());
        map = new HashMap<>();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerviewOrderList.setLayoutManager(manager);
        recyclerviewOrderList.setAdapter(adapter);

        String uid = auth.getCurrentUser().getUid();

        database.getReference().child("order list").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOrder.clear();
                for(DataSnapshot dataOrder : snapshot.getChildren()){
                    OrderList order = new OrderList();
                    order.setNama(dataOrder.child("nama").getValue().toString());
                    order.setImagePath(dataOrder.child("image path").getValue().toString());
                    order.setHarga(Integer.parseInt(dataOrder.child("harga").getValue().toString()));
                    order.setJumlah(Integer.parseInt(dataOrder.child("jumlah").getValue().toString()));
                    listOrder.add(order);
                }
                adapter.notifyDataSetChanged();
                int totalHarga = hitungTotalHarga();
                txtHargaOrderList.setText(String.valueOf("Rp " + totalHarga));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnOrderOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date tanggal = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String tanggalFix = dateFormat.format(tanggal);

                for(OrderList order : listOrder){
                    map.put("nama", order.getNama());
                    map.put("harga", order.getHarga());
                    map.put("jumlah", order.getJumlah());
                    map.put("image path", order.getImagePath());
                    map.put("tanggal", tanggalFix);
                    database.getReference().child("order history").child(uid).child(order.getNama()).updateChildren(map);
                    database.getReference().child("order list").child(uid).removeValue();
                }

                Intent intent = new Intent(getBaseContext(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        imgPrevOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        imgNextOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderHistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });



    }

    private int hitungTotalHarga(){
        int total = 0;
        if(listOrder.size() > 0) {
            for (OrderList order : listOrder) {
                total += (order.getHarga() * order.getJumlah());
            }
        }
        return total;
    }
}