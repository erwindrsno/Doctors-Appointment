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

    public Dokter getDokter(int position){
        return dokters.get(position);
    }

    public void editDokter(int position, String namaD, String spesialis, String nomorHP, String detail){
        this.dokters.get(position).setNama(namaD);
        this.dokters.get(position).setSpesialis(spesialis);
        this.dokters.get(position).setNomorHP(nomorHP);
        this.dokters.get(position).setDetail(detail);
        Dokter dt = new Dokter(namaD, spesialis, nomorHP, detail);
        this.uidokter.updateListDokter(dokters);
    }

    //pertemuan

    public ArrayList<Dokter> getDokters(){
        return this.dokters;
    }

    public int getSizeDokter(){
        return dokters.size();
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
