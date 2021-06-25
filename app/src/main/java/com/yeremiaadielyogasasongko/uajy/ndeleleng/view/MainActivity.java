package com.yeremiaadielyogasasongko.uajy.ndeleleng.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.ActivityMainBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.fragments.FragmentDetailPesanan;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.fragments.FragmentMenu;

public class MainActivity extends AppCompatActivity {

    private ActivityViewModel viewModel;
    private ActivityMainBinding binding;

    public static final int REQUEST_CODE = 420;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);

        viewModel.loadMenu();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupBtnListener();

        viewModel.getListMenuMutableLiveData().observe(this, data -> {
            if (data != null) {
                FragmentMenu menuFragment = new FragmentMenu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerview, menuFragment)
                        .commit();
            }
        });

        viewModel.getMessageMutableLiveData().observe(this, data -> {
            if (data != null && !data.isEmpty()) {
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            }
        });

        // init tampilan utama ke menu
    }

    private void setupBtnListener() {
        binding.ScanBtn.setOnClickListener(view -> {
            startActivityForResult(new Intent(MainActivity.this, ScanActivity.class), 420);
        });


        viewModel.getPesananDaoMutableLiveData().observe(this, data -> {
            if (data != null) {
                binding.DetailPesananBtn.setOnClickListener(view -> {
                    FragmentDetailPesanan detailPesanan = new FragmentDetailPesanan();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerview, detailPesanan)
                            .addToBackStack(null)
                            .commit();
                });
            } else {
                binding.DetailPesananBtn.setOnClickListener(view -> {
                    Toast.makeText(this, "SCAN QR DULU", Toast.LENGTH_SHORT).show();
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            try {
                String _qrResult = data.getStringExtra("qrResult");
                viewModel.createPesanan(_qrResult);
            } catch (Exception e) {
                Log.d("string", "onActivityResult: " + e.getMessage());
                Toast.makeText(this, "QR Code Tidak Valid", Toast.LENGTH_SHORT).show();
            }
        }
    }
}