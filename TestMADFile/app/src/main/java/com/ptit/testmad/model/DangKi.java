package com.ptit.testmad.model;

public class DangKi {
    private int idLop;
    private int idSV;
    private int kihoc;
    private int stc;
    private String tenSv;
    private String tenLop;

    public DangKi( int idLop, int idSV, int kihoc, int stc, String tenSv, String tenLop) {

        this.idLop = idLop;
        this.idSV = idSV;
        this.kihoc = kihoc;
        this.stc = stc;
        this.tenSv = tenSv;
        this.tenLop = tenLop;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public DangKi() {
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public int getKihoc() {
        return kihoc;
    }

    public void setKihoc(int kihoc) {
        this.kihoc = kihoc;
    }

    public int getStc() {
        return stc;
    }

    public void setStc(int stc) {
        this.stc = stc;
    }


    public String toString() {
        return idSV+"_"+idLop+"_"+stc+"_"+kihoc+"_"+tenSv+"_"+tenLop+";";
    }

    public String toStringLop() {
        return idLop+"_"+tenLop+"_"+stc+"_"+kihoc+";";
    }
    public String toStringSinhVien() {
        return idSV+"_"+tenSv+"_"+"_"+stc+"_"+kihoc+";";
    }
}
