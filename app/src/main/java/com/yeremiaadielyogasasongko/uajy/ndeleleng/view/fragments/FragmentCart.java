package com.yeremiaadielyogasasongko.uajy.ndeleleng.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter.AdapterCart;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter.AdapterMenu;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.FragmentCartBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.ActivityViewModel;

import org.jetbrains.annotations.NotNull;

public class FragmentCart extends Fragment implements AdapterCart.OnCartClickListener {

    private ActivityViewModel viewModel;
    private FragmentCartBinding binding;
    private AdapterCart adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);

        viewModel.getListCartMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            setupRecyclerView();
        });

        binding.btnConfirm.setOnClickListener(v -> {
//            Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            viewModel.createDetailPesanan();
            requireActivity().onBackPressed();
        });
    }

    public void setupRecyclerView() {
        adapter = new AdapterCart(viewModel.getListCartMutableLiveData().getValue(), this);
        binding.recyclerCart.setAdapter(adapter);
        binding.recyclerCart.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }

    @Override
    public void onIncrement(String id) {
        viewModel.incrementCart(id);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDecrement(String id) {
        viewModel.decrementCart(id);
        adapter.notifyDataSetChanged();
    }
}