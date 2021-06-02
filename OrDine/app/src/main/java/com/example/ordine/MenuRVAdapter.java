package com.example.ordine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Menu;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuViewHolder> {
    private ArrayList<Menu> listMenu;
    private Menu menu = new Menu();

    public MenuRVAdapter(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public MenuRVAdapter() {

    }

    @NonNull
    @Override
    public MenuRVAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRVAdapter.MenuViewHolder holder, int position) {
        holder.txtNamaCardviewMenu.setText(listMenu.get(position).getNama());
        holder.txtHargaCardviewMenu.setText(String.valueOf(listMenu.get(position).getHarga()));
        holder.txtGenreCardviewMenu.setText(String.valueOf(listMenu.get(position).getGenre()));
        // todo: tambahi imgCardviewMenu
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaCardviewMenu, txtHargaCardviewMenu, txtGenreCardviewMenu;
        private ImageView imgCardviewMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaCardviewMenu = itemView.findViewById(R.id.txtNamaCardviewMenu);
            txtHargaCardviewMenu = itemView.findViewById(R.id.txtHargaCardviewMenu);
            imgCardviewMenu = itemView.findViewById(R.id.imgCardviewMenu);
            txtGenreCardviewMenu = itemView.findViewById(R.id.txtGenreCardviewMenu);
        }
    }

    // todo: tambahi interface untuk recyclerview click listener
}
