package com.example.gws;

import android.util.Log;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Dokter> dokters;
    protected InterfaceDokter uidokter;

    protected ArrayList<Pertemuan> pertemuans;
    protected InterfacePertemuan uipertemuan;

    public MainPresenter(InterfaceDokter uidokter, InterfacePertemuan uipertemuan){
        this.uidokter = uidokter;
        this.dokters = new ArrayList<>();

        this.uipertemuan = uipertemuan;
        this.pertemuans = new ArrayList<>();
    }

    public void delListDokter(int position){
        dokters.remove(position);
        this.uidokter.updateListDokter(dokters);
    }

    public void addListDokter(Dokter dokter){
        dokters.add(dokter);
        this.uidokter.updateListDokter(dokters);
    }

    public void toggleFavDokter(int position){
        Log.d("debug", "dokter debug"+dokters.get(position).isFav());
        dokters.get(position).toggleFav();
    }

    //pertemuan

    public ArrayList<Dokter> getDokters(){
        return this.dokters;
    }

    public void delListPertemuan(int position){
        pertemuans.remove(position);
        this.uipertemuan.updateListPertemuan(pertemuans);
    }

    public void addListPertemuan(Pertemuan pertemuan){
        pertemuans.add(pertemuan);
        this.uipertemuan.updateListPertemuan(pertemuans);
    }

    public void toggleDonePertemuan(int position){
        Log.d("debug", "dokter debug"+pertemuans.get(position).isDone());
        pertemuans.get(position).isDone();
    }

}
