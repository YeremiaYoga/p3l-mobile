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
import android.widget.Toast;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.adapter.AdapterMenu;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.FragmentMenuBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.MenuDao;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.ActivityViewModel;

import org.jetbrains.annotations.NotNull;

public class FragmentMenu extends Fragment implements AdapterMenu.OnItemClickListener {

    private ActivityViewModel viewModel;
    private FragmentMenuBinding binding;
    private AdapterMenu adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);

        viewModel.getPesananDaoMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            setupRecyclerView();
        });

        viewModel.getPesananDaoMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            adapter.setSudahScanQr(data != null);
        });

        viewModel.getListCartMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.btnCart.setVisibility(
                    data.size() > 0 ? View.VISIBLE : View.GONE
            );

            binding.btnCart.setOnClickListener(v -> {

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.containerview, new FragmentCart())
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(), data.size() + "", Toast.LENGTH_SHORT).show();
            });
        });

    }

    public void setupRecyclerView() {
        adapter = new AdapterMenu(viewModel.getListMenuMutableLiveData().getValue(), this);
        binding.recyclermenu.setAdapter(adapter);
        binding.recyclermenu.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }

    @Override
    public void onItemClick(MenuDao menu) {
        viewModel.tambahCart(menu);
        Toast.makeText(getContext(), "Berhasil Menambahkan " + menu.getNama_menu(), Toast.LENGTH_SHORT).show();
    }
}