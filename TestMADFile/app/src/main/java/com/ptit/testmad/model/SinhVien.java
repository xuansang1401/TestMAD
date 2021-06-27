package com.ptit.testmad.model;

public class SinhVien {
    private int id;
    private String ten;
    private int nam;
    private String quequan;
    private int namhoc;

    public SinhVien() {
    }

    public SinhVien(int id, String ten, int nam, String quequan, int namhoc) {
        this.id = id;
        this.ten = ten;
        this.nam = nam;
        this.quequan = quequan;
        this.namhoc = namhoc;
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

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public int getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(int namhoc) {
        this.namhoc = namhoc;
    }
    public String toString(){
        return id+"_"+ten+"_"+nam+"_"+quequan+"_"+namhoc+";";
    }
}
