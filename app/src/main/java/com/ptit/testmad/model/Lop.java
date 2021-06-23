package com.ptit.testmad.model;

import android.util.Log;

public class Lop {
    private int idLop;
    private String tenLop;
    private String moTa;

    public Lop(){}
    public Lop(String tenLop, String moTa) {
        this.tenLop = tenLop;
        this.moTa = moTa;
    }

    public Lop(int idLop, String tenLop, String moTa) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.moTa = moTa;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
