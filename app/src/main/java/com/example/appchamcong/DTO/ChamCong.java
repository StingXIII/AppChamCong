package com.example.appchamcong.DTO;

public class ChamCong {
    int ID;
    String MANV;
    String TENNV;
    String PHONGBAN;
    String NGAYCONG;
    String THANGCONG;
    String GIOVAO;
    String GIORA;
    int GIOCONG;

    public ChamCong(int ID,String MANV, String TENNV, String PHONGBAN, String NGAYCONG, String THANGCONG, String GIOVAO, String GIORA, int GIOCONG) {
        this.ID = ID;
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.PHONGBAN = PHONGBAN;
        this.NGAYCONG = NGAYCONG;
        this.THANGCONG = THANGCONG;
        this.GIOVAO = GIOVAO;
        this.GIORA = GIORA;
        this.GIOCONG = GIOCONG;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }

    public String getPHONGBAN() {
        return PHONGBAN;
    }

    public void setPHONGBAN(String PHONGBAN) {
        this.PHONGBAN = PHONGBAN;
    }

    public String getNGAYCONG() {
        return NGAYCONG;
    }

    public void setNGAYCONG(String NGAYCONG) {
        this.NGAYCONG = NGAYCONG;
    }

    public String getTHANGCONG() {
        return THANGCONG;
    }

    public void setTHANGCONG(String THANGCONG) {
        this.THANGCONG = THANGCONG;
    }

    public String getGIOVAO() {
        return GIOVAO;
    }

    public void setGIOVAO(String GIOVAO) {
        this.GIOVAO = GIOVAO;
    }

    public String getGIORA() {
        return GIORA;
    }

    public void setGIORA(String GIORA) {
        this.GIORA = GIORA;
    }

    public int getGIOCONG() {
        return GIOCONG;
    }

    public void setGIOCONG(int GIOCONG) {
        this.GIOCONG = GIOCONG;
    }

}
