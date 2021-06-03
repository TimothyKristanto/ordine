package com.example.ordine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.FoodMenu;

public class MenuActivity extends AppCompatActivity implements MenuRVAdapter.RecyclerViewClickListener{

    private ImageView imgPrevMenu;
    private ImageView imgNextMenu;
    private RecyclerView recyclerviewMenu;
    private Button btnAddMenu;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ArrayList<FoodMenu> listFoodMenus;
    private MenuRVAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgPrevMenu = findViewById(R.id.imgPrevMenu);
        imgNextMenu = findViewById(R.id.imgNextMenu);
        recyclerviewMenu = findViewById(R.id.recyclerviewMenu);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");
        listFoodMenus = new ArrayList<>();
        adapter = new MenuRVAdapter(listFoodMenus, getApplicationContext(), this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerviewMenu.setLayoutManager(manager);
        recyclerviewMenu.setAdapter(adapter);

        database.getReference().child("menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listFoodMenus.clear();
                for(DataSnapshot dataMenu : snapshot.getChildren()){
                    FoodMenu foodMenu = new FoodMenu();
                    foodMenu.setNama(dataMenu.child("nama").getValue().toString());
                    foodMenu.setGenre(dataMenu.child("genre").getValue().toString());
                    foodMenu.setImagePath(dataMenu.child("image link").getValue().toString());
                    foodMenu.setHarga(Integer.parseInt(dataMenu.child("harga").getValue().toString()));
                    listFoodMenus.add(foodMenu);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgPrevMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        imgNextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });



    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MenuActivity.this, MenuDetailsActivity.class);
        intent.putExtra("namaMakanan", listFoodMenus.get(position).getNama());
        intent.putExtra("genre", listFoodMenus.get(position).getGenre());
        intent.putExtra("harga", listFoodMenus.get(position).getHarga());
        intent.putExtra("imagePath", listFoodMenus.get(position).getImagePath());
        startActivity(intent);
    }
}