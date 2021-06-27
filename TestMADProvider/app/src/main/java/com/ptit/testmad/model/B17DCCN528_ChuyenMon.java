package com.ptit.testmad.model;

public class B17DCCN528_ChuyenMon {
    private int id;
    private String ten;
    private String moTa;

    public B17DCCN528_ChuyenMon(){}

    public B17DCCN528_ChuyenMon(int id, String ten, String moTa) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
    }

    public B17DCCN528_ChuyenMon(String ten, String moTa) {
        this.ten = ten;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
