package com.example.gws;

import java.sql.Time;
import java.sql.Date;

public class Pertemuan {
    private Date tanggal;
    private Time waktu;
    private String namaPasien;
    private String namaDokter;
    private String keluhan;
    private int umur;
    private String gender;


    public Pertemuan(Date tanggal, Time waktu, String namaPasien, String namaDokter, String keluhan, int umur, String gender) {
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.namaPasien = namaPasien;
        this.namaDokter = namaDokter;
        this.keluhan = keluhan;
        this.umur = umur;
        this.gender = gender;
    }


    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getWaktu() {
        return waktu;
    }

    public void setWaktu(Time waktu) {
        this.waktu = waktu;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
