package com.example.gws;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.gws.databinding.FragmentDokterDialogBinding;

public class DokterDialogFragment extends DialogFragment {
    private String nama;
    private String spesialis;
    private String noHP;
    private String detail;
    private FragmentDokterDialogBinding binding;

    public DokterDialogFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentDokterDialogBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    public static DokterDialogFragment newInstance(String title){
        DokterDialogFragment fragment = new DokterDialogFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        binding.tvDokterNameDialog.setText(this.nama);
        binding.tvDokterSpesialisDialog.setText(this.spesialis);
        binding.tvDokterNoHPDialog.setText(this.noHP);
        binding.tvDokterDetailDialog.setText(this.detail);
    }

    public void setMessage(String nama, String spesialis, String noHP, String detail){
        Log.d("debug dialog", "masuk dialog set message");
        this.nama = nama;
        this.spesialis = spesialis;
        this.noHP = noHP;
        this.detail = detail;
    }
}
