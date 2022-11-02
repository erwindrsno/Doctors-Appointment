package com.example.gws;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;
import com.example.gws.databinding.FragmentBuatPertemuanDialogBinding;

public class PertemuanDialogFragment extends DialogFragment {
    private String tanggal;
    private String waktu;
    private String namapasien;
    private String namadokter;
    private String spesialis;
    private String keluhan;
    private FragmentBuatPertemuanDialogBinding binding;

    public PertemuanDialogFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentBuatPertemuanDialogBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    public static PertemuanDialogFragment newInstance(String title){
        PertemuanDialogFragment fragment = new PertemuanDialogFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        binding.dialogTanggal.setText(this.tanggal);
        binding.dialogWaktu.setText(this.waktu);
        binding.dialogNamaPasien.setText(this.namapasien);
        binding.dialogNamaDokter.setText(this.namadokter);
        binding.dialogSpesialis.setText(this.spesialis);
        binding.dialogKeluhan.setText(this.keluhan);
    }

    public void setMessagePertemuan(String tanggal, String waktu, String namapasien, String namadokter, String spesialis, String keluhan){
        Log.d("debug dialog", "masuk dialog set message");
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.namapasien = namapasien;
        this.namadokter = namadokter;
        this.spesialis = spesialis;
        this.keluhan = keluhan;
    }
}
