package com.example.gws;

public class Dokter {

    private int id;
    private boolean isFav;
    private String nama;
    private String spesialis;
    private String nomorHP;
    private String detail;

    public Dokter(int id, String nama, String spesialis, String nomorHP, String detail) {
        this.id = id;
        this.nama = nama;
        this.spesialis = spesialis;
        this.nomorHP = nomorHP;
        this.detail = detail;
        this.isFav = false;
    }

    public Dokter(int id, String nama, String spesialis, String nomorHP) {
        this.id = id;
        this.nama = nama;
        this.spesialis = spesialis;
        this.nomorHP = nomorHP;
        this.detail = "";
        this.isFav = false;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isFav() {
        return isFav;
    }

    public void toggleFav(){
        if(isFav) isFav =false;
        else isFav = true;
    }
}
