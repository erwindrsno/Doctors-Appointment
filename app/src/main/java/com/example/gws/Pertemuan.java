package com.example.gws;

import java.util.Date;
import java.util.Date;

public class Pertemuan {
    private String tanggal;
    private String waktu;
    private String namaPasien;
    private String namaDokter;
    private String keluhan;
    private String spesialis;
    private String gender;
    private boolean done;

    public Pertemuan(String tanggal, String waktu, String namaPasien, String namaDokter, String spesialis, String keluhan, String gender, boolean done) {
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.namaPasien = namaPasien;
        this.namaDokter = namaDokter;
        this.keluhan = keluhan;
        this.spesialis = spesialis;
        this.gender = gender;
        this.done = done;
    }


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
