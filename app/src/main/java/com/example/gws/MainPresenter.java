package com.example.gws;

import android.util.Log;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Dokter> dokters;
    protected InterfaceDokter uidokter;

    public MainPresenter(InterfaceDokter uidokter){
        this.uidokter = uidokter;
        this.dokters = new ArrayList<>();
    }

    public void delListDokter(int position){
        dokters.remove(position);
        this.uidokter.updateList(dokters);
    }

    public void addListDokter(Dokter dokter){
        dokters.add(dokter);
        this.uidokter.updateList(dokters);
    }

    public void toggleFavDokter(int position){
        Log.d("debug", "dokter debug"+dokters.get(position).isFav());
        dokters.get(position).toggleFav();
    }

}