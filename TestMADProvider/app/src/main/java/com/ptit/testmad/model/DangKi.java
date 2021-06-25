package com.ptit.testmad.model;

public class DangKi {
    private int id;
    private int idLop;
    private int idSV;
    private int kihoc;
    private int stc;

    public DangKi(int id, int idLop, int idSV, int kihoc, int stc) {
        this.id = id;
        this.idLop = idLop;
        this.idSV = idSV;
        this.kihoc = kihoc;
        this.stc = stc;
    }

    public DangKi(int idLop, int idSV, int kihoc, int stc) {
        this.idLop = idLop;
        this.idSV = idSV;
        this.kihoc = kihoc;
        this.stc = stc;
    }

    public DangKi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
