package com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.AdapterDetailBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.DetailPesananDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterDetailPesanan extends RecyclerView.Adapter<AdapterDetailPesanan.DetailPesananViewHolder> {

    private List<DetailPesananDao> listDao;

    public AdapterDetailPesanan(List<DetailPesananDao> listDao) {
        this.listDao = listDao;
    }

    @NonNull
    @NotNull
    @Override
    public DetailPesananViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new DetailPesananViewHolder(
                AdapterDetailBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterDetailPesanan.DetailPesananViewHolder holder, int position) {
        holder.bind(listDao.get(position));
    }

    @Override
    public int getItemCount() {
        return listDao.size();
    }

    class DetailPesananViewHolder extends RecyclerView.ViewHolder {

        AdapterDetailBinding binding;

        public DetailPesananViewHolder(AdapterDetailBinding _binding) {
            super(_binding.getRoot());
            this.binding = _binding;
        }

        public void bind(DetailPesananDao dp) {

            String title = dp.getNamaMenu() + " @" + dp.getJumlah();

            binding.namaMenu.setText(title);
            switch (dp.getStatus()) {
                case 0:
                    binding.statusPesanan.setText("Menunggu");
                    break;
                case 1:
                    binding.statusPesanan.setText("Selesai");
                    break;
            }

            int total = dp.getJumlah() * dp.getHarga();
            String bayar = "Rp. " + total;
            binding.hargaMenu.setText(bayar);

            Glide.with(binding.getRoot())
                    .load(dp.getGambar())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.gambarMenu);
        }
    }
}
