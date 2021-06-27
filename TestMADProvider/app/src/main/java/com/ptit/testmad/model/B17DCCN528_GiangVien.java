package com.ptit.testmad.model;

public class B17DCCN528_GiangVien {
    private int id;
    private String ten;
    private String trinhDo;
    private int soNamKN;


    public B17DCCN528_GiangVien() {
    }


    public B17DCCN528_GiangVien(int id, String ten, String trinhDo, int soNamKN) {
        this.id = id;
        this.ten = ten;
        this.trinhDo = trinhDo;
        this.soNamKN = soNamKN;
    }

    public B17DCCN528_GiangVien(String ten, String trinhDo, int soNamKN) {
        this.ten = ten;
        this.trinhDo = trinhDo;
        this.soNamKN = soNamKN;
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

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public int getSoNamKN() {
        return soNamKN;
    }

    public void setSoNamKN(int soNamKN) {
        this.soNamKN = soNamKN;
    }
}
