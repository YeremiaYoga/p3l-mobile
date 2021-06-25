package com.yeremiaadielyogasasongko.uajy.ndeleleng.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.R;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.databinding.FragmentNotaBinding;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.DetailPesananDao;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.view.ActivityViewModel;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class FragmentNota extends Fragment {

    public FragmentNota() {
        // Required empty public constructor
    }

    private ActivityViewModel viewModel;
    private FragmentNotaBinding binding;

    private Float subTotal = 0.0F;

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal += subTotal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNotaBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);

        init();


        float _service = this.getSubTotal() * 0.05F;
        float _pajak = this.getSubTotal() * 0.1F;

        String subTotal = "SUBTOTAL: Rp. " + this.getSubTotal();
        String service = "SERVICE: Rp. " + _service;
        String pajak = "PAJAK: Rp." + _pajak;
        String totalBayar = "TOTAL BAYAR: " + (this.getSubTotal() + _service + _pajak);

        binding.subTotal.setText(subTotal);
        binding.service.setText(service);
        binding.pajak.setText(pajak);
        binding.totalBayar.setText(totalBayar);

        binding.btnBack.setOnClickListener(v -> {
            viewModel.finish();

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerview, new FragmentMenu())
                    .commit();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void init() {
        TableRow thead = new TableRow(requireContext());

        TextView col_1 = new TextView(requireContext());
        TextView col_2 = new TextView(requireContext());
        TextView col_3 = new TextView(requireContext());
        TextView col_4 = new TextView(requireContext());
        TextView col_5 = new TextView(requireContext());

        TextView col_empty_1 = new TextView(requireContext());
        TextView col_empty_2 = new TextView(requireContext());
        TextView col_empty_3 = new TextView(requireContext());
        TextView col_empty_4 = new TextView(requireContext());
        TextView col_empty_5 = new TextView(requireContext());

        col_1.setText("NO");
        col_2.setText("MENU");
        col_3.setText("QTY");
        col_4.setText("HARGA");
        col_5.setText("TOTAL");

        col_empty_1.setText("    ");
        col_empty_2.setText("    ");
        col_empty_3.setText("    ");
        col_empty_4.setText("    ");
        col_empty_5.setText("    ");

        thead.addView(col_1);
        thead.addView(col_empty_1);
        thead.addView(col_2);
        thead.addView(col_empty_2);
        thead.addView(col_3);
        thead.addView(col_empty_3);
        thead.addView(col_4);
        thead.addView(col_empty_4);
        thead.addView(col_5);
        thead.addView(col_empty_5);

        binding.tableMain.addView(thead);

        List<DetailPesananDao> list = viewModel.getListDetailPesananMutableLiveData().getValue();

        for (int i = 0; i < list.size(); i++) {
            TableRow tbRow = new TableRow(requireContext());
            TextView c1 = new TextView(requireContext());
            TextView c2 = new TextView(requireContext());
            TextView c3 = new TextView(requireContext());
            TextView c4 = new TextView(requireContext());
            TextView c5 = new TextView(requireContext());

            TextView c_empty_1 = new TextView(requireContext());
            TextView c_empty_2 = new TextView(requireContext());
            TextView c_empty_3 = new TextView(requireContext());
            TextView c_empty_4 = new TextView(requireContext());
            TextView c_empty_5 = new TextView(requireContext());

            c_empty_1.setText("    ");
            c_empty_2.setText("    ");
            c_empty_3.setText("    ");
            c_empty_4.setText("    ");
            c_empty_5.setText("    ");

            String str = String.valueOf(i + 1);

            c1.setText(str);
            c2.setText(list.get(i).getNamaMenu());
            c3.setText(String.valueOf(list.get(i).getKuantiti()));
            c4.setText(String.valueOf(list.get(i).getHarga()));
            float total = list.get(i).getKuantiti() * list.get(i).getHarga();
            this.setSubTotal(total);

            c5.setText(String.valueOf(total));

            tbRow.addView(c1);
            tbRow.addView(c_empty_1);
            tbRow.addView(c2);
            tbRow.addView(c_empty_2);
            tbRow.addView(c3);
            tbRow.addView(c_empty_3);
            tbRow.addView(c4);
            tbRow.addView(c_empty_4);
            tbRow.addView(c5);
            tbRow.addView(c_empty_5);

            binding.tableMain.addView(tbRow);
        }
    }

}