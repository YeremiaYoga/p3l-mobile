package com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.AdapterMenuBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.MenuDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MenuViewHolder> {

    private List<MenuDao> dataList;
    private List<MenuDao> filteredDataList;
    private OnItemClickListener listener;

    private boolean sudahScanQr = false;

    public AdapterMenu(List<MenuDao> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.filteredDataList = this.dataList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(
                AdapterMenuBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterMenu.MenuViewHolder holder, int position) {
        holder.bind(filteredDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private AdapterMenuBinding binding;

        public MenuViewHolder(AdapterMenuBinding _binding) {
            super(_binding.getRoot());
            this.binding = _binding;
        }

        public void bind(MenuDao menu) {
            binding.judulmenu.setText(menu.getNama_menu());
            binding.deskripsimenu.setText(menu.getDeskripsi_menu());
            binding.harga.setText(menu.getHarga());

            binding.beli.setEnabled(sudahScanQr);
            
            binding.beli.setOnClickListener(view -> {
                listener.onItemClick(menu);
            });

            Glide.with(binding.getRoot())
                    .load(menu.getGambar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.imek);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MenuDao menu);
    }

    public void setSudahScanQr(boolean sudahScanQr) {
        this.sudahScanQr = sudahScanQr;
        notifyDataSetChanged();
    }
}
