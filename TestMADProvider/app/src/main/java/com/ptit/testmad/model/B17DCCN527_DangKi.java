package com.ptit.testmad.model;

public class B17DCCN527_DangKi {
    private int id;
    private int idGV;
    private int idCM;
    private int soNamKn;


    public B17DCCN527_DangKi() {
    }

    public B17DCCN527_DangKi(int idGV, int idCM, int soNamKn) {
        this.idGV = idGV;
        this.idCM = idCM;
        this.soNamKn = soNamKn;
    }

    public B17DCCN527_DangKi(int id, int idGV, int idCM, int soNamKn) {
        this.id = id;
        this.idGV = idGV;
        this.idCM = idCM;
        this.soNamKn = soNamKn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGV() {
        return idGV;
    }

    public void setIdGV(int idGV) {
        this.idGV = idGV;
    }

    public int getIdCM() {
        return idCM;
    }

    public void setIdCM(int idCM) {
        this.idCM = idCM;
    }

    public int getSoNamKn() {
        return soNamKn;
    }

    public void setSoNamKn(int soNamKn) {
        this.soNamKn = soNamKn;
    }
}
