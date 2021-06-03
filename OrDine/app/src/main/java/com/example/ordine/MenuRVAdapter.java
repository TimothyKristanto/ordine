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

import Model.FoodMenu;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuViewHolder> {
    private ArrayList<FoodMenu> listFoodMenus;
    private Context context;
    private RecyclerViewClickListener listener;

    public MenuRVAdapter(ArrayList<FoodMenu> listFoodMenus, Context context, RecyclerViewClickListener listener) {
        this.listFoodMenus = listFoodMenus;
        this.context = context;
        this.listener = listener;
    }

    public MenuRVAdapter() {

    }

    @NonNull
    @Override
    public MenuRVAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_menu, parent, false);
        return new MenuViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRVAdapter.MenuViewHolder holder, int position) {
        holder.txtNamaCardviewMenu.setText(listFoodMenus.get(position).getNama());
        holder.txtHargaCardviewMenu.setText("Rp " + String.valueOf(listFoodMenus.get(position).getHarga()));
        holder.txtGenreCardviewMenu.setText(String.valueOf(listFoodMenus.get(position).getGenre()));
        Glide.with(context).load(listFoodMenus.get(position).getImagePath()).into(holder.imgCardviewMenu);
    }

    @Override
    public int getItemCount() {
        return listFoodMenus.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNamaCardviewMenu, txtHargaCardviewMenu, txtGenreCardviewMenu;
        private ImageView imgCardviewMenu;
        private RecyclerViewClickListener recyclerViewClickListener;

        public MenuViewHolder(@NonNull View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            txtNamaCardviewMenu = itemView.findViewById(R.id.txtNamaCardviewMenu);
            txtHargaCardviewMenu = itemView.findViewById(R.id.txtHargaCardviewMenu);
            imgCardviewMenu = itemView.findViewById(R.id.imgCardviewMenu);
            txtGenreCardviewMenu = itemView.findViewById(R.id.txtGenreCardviewMenu);
            this.recyclerViewClickListener = recyclerViewClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(getAdapterPosition());
        }
    }

    // todo: tambahi interface untuk recyclerview click listener
    public interface RecyclerViewClickListener{
        void onClick(int position);
    }
}
