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

import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter.AdapterDetailPesanan;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.FragmentDetailPesananBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.ActivityViewModel;

import org.jetbrains.annotations.NotNull;

public class FragmentDetailPesanan extends Fragment {

    private FragmentDetailPesananBinding binding;
    private AdapterDetailPesanan adapter;
    private ActivityViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailPesananBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        viewModel.loadDetailPesanan();

        viewModel.getListDetailPesananMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                setupRecyclerView();
                binding.btnFinish.setVisibility(View.VISIBLE);
            }
        });

        binding.btnFinish.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerview, new FragmentNota())
                    .commit();
        });
    }

    private void setupRecyclerView() {
        adapter = new AdapterDetailPesanan(viewModel.getListDetailPesananMutableLiveData().getValue());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;

    }
}