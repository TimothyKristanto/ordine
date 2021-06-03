package com.example.ordine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import Model.OrderHistory;

public class OrderHistoryRVAdapter extends RecyclerView.Adapter<OrderHistoryRVAdapter.OrderHistoryViewHolder> {

    private ArrayList<OrderHistory> listOrderHistory;
    private Context context;

    public OrderHistoryRVAdapter(ArrayList<OrderHistory> listOrderHistory, Context context) {
        this.listOrderHistory = listOrderHistory;
        this.context = context;
    }

    public OrderHistoryRVAdapter() {
        listOrderHistory = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderHistoryRVAdapter.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_orderhistory, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryRVAdapter.OrderHistoryViewHolder holder, int position) {
        holder.txtNamaCardviewOrderHistory.setText(listOrderHistory.get(position).getNama());
        holder.txtHargaCardviewOrderHistory.setText("Rp " + listOrderHistory.get(position).getHarga());
        holder.txtTanggalCardviewOrderHistory.setText((CharSequence) listOrderHistory.get(position).getTanggal());
        Glide.with(context).load(listOrderHistory.get(position).getImageurl()).into(holder.imgCardviewOrderHistory);
    }

    @Override
    public int getItemCount() {
        return listOrderHistory.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaCardviewOrderHistory, txtHargaCardviewOrderHistory, txtTanggalCardviewOrderHistory;
        private ImageView imgCardviewOrderHistory;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaCardviewOrderHistory = itemView.findViewById(R.id.txtNamaCardviewOrderHistory);
            txtHargaCardviewOrderHistory = itemView.findViewById(R.id.txtHargaCardviewOrderHistory);
            imgCardviewOrderHistory = itemView.findViewById(R.id.imgCardviewOrderHistory);
            txtTanggalCardviewOrderHistory = itemView.findViewById(R.id.txtTanggalCardviewOrderHistory);
        }
    }
}
