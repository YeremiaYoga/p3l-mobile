package com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.AdapterCartBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.CartDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartViewModel> {
    private List<CartDao> listCart;
    private OnCartClickListener listener;

    public AdapterCart(List<CartDao> listCart, OnCartClickListener listener) {
        this.listCart = listCart;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewModel onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CartViewModel(
                AdapterCartBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCart.CartViewModel holder, int position) {
        holder.bind(listCart.get(position));
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    class CartViewModel extends RecyclerView.ViewHolder {
        AdapterCartBinding binding;

        public CartViewModel(AdapterCartBinding _binding) {
            super(_binding.getRoot());
            this.binding = _binding;
        }

        public void bind(CartDao cartDao) {
            binding.namaMenu.setText(cartDao.getMenu().getNama_menu());

            int total = cartDao.getJumlah() * Integer.parseInt(cartDao.getMenu().getHarga());
            String str = "Rp. " + total;

            binding.hargaMenu.setText(str);

            String jml = String.valueOf(cartDao.getJumlah());

            binding.jmlMenu.setText(jml);
            binding.btnKurang.setOnClickListener(view -> {
                listener.onDecrement(cartDao.getMenu().getId());
            });

            binding.btnTambah.setOnClickListener(view -> {
                listener.onIncrement(cartDao.getMenu().getId());
            });
        }
    }

    public interface OnCartClickListener {
        void onIncrement(String id);

        void onDecrement(String id);
    }
}
