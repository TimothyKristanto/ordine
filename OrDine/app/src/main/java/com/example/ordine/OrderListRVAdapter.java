package com.example.ordine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Model.OrderList;

public class OrderListRVAdapter extends RecyclerView.Adapter<OrderListRVAdapter.OrderListViewHolder> {
    private ArrayList<OrderList> listOrder;
    private Context context;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");

    public OrderListRVAdapter(ArrayList<OrderList> listOrder, Context context) {
        this.listOrder = listOrder;
        this.context = context;
    }

    public OrderListRVAdapter() {
    }

    @NonNull
    @Override
    public OrderListRVAdapter.OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_orderlist, parent, false);
        return new OrderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListRVAdapter.OrderListViewHolder holder, int position) {
        holder.txtNamaCardviewOrderList.setText(listOrder.get(position).getNama());
        holder.txtHargaCardviewOrderList.setText(String.valueOf(listOrder.get(position).getHarga()));
        holder.txtJumlahCardviewOrderList.setText(String.valueOf(listOrder.get(position).getJumlah()));
        Glide.with(context).load(listOrder.get(position).getImagePath()).into(holder.imgCardviewOrderList);

        holder.imgDeleteCardviewOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("order list").child(auth.getCurrentUser().getUid()).child(listOrder.get(position).getNama()).removeValue();
                listOrder.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCardviewOrderList, imgDeleteCardviewOrderList;
        private TextView txtNamaCardviewOrderList, txtHargaCardviewOrderList, txtJumlahCardviewOrderList;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCardviewOrderList = itemView.findViewById(R.id.imgCardviewOrderList);
            txtNamaCardviewOrderList = itemView.findViewById(R.id.txtNamaCardviewOrderList);
            txtHargaCardviewOrderList = itemView.findViewById(R.id.txtHargaCardviewOrderList);
            imgDeleteCardviewOrderList = itemView.findViewById(R.id.imgDeleteCardviewOrderList);
            txtJumlahCardviewOrderList = itemView.findViewById(R.id.txtJumlahCardviewOrderList);
        }
    }
}
