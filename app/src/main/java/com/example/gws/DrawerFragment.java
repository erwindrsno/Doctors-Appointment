package com.example.gws;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentDrawerBinding;
import com.example.gws.databinding.FragmentHomeBinding;

public class DrawerFragment extends Fragment{
    private FragmentDrawerBinding binding;
    private AdapterMenu adapterMenu;

    public DrawerFragment(){
        //Konstruktor harus kosong
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentDrawerBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        this.adapterMenu = new AdapterMenu(this,inflater);
        binding.lvMenu.setAdapter(adapterMenu);
        adapterMenu.addList("Home");
        adapterMenu.addList("Pertemuan");
        adapterMenu.addList("Dokter");
        adapterMenu.addList("Exit");
        Log.d("debugKeAddGak","oke sip");
        return view;
    }
}