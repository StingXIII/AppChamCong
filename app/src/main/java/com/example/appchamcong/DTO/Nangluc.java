package com.example.appchamcong.DTO;

public class Nangluc {
    String MANHANVIEN,TENNHANVIEN;
    int KQ,DIEM,XEPLOAI;

    public Nangluc(String MANHANVIEN, String TENNHANVIEN, int KQ, int DIEM, int XEPLOAI) {
        this.MANHANVIEN = MANHANVIEN;
        this.TENNHANVIEN = TENNHANVIEN;
        this.KQ = KQ;
        this.DIEM = DIEM;
        this.XEPLOAI = XEPLOAI;
    }

    public String getMANHANVIEN() {
        return MANHANVIEN;
    }

    public void setMANHANVIEN(String MANHANVIEN) {
        this.MANHANVIEN = MANHANVIEN;
    }

    public String getTENNHANVIEN() {
        return TENNHANVIEN;
    }

    public void setTENNHANVIEN(String TENNHANVIEN) {
        this.TENNHANVIEN = TENNHANVIEN;
    }

    public int getKQ() {
        return KQ;
    }

    public void setKQ(int KQ) {
        this.KQ = KQ;
    }

    public int getDIEM() {
        return DIEM;
    }

    public void setDIEM(int DIEM) {
        this.DIEM = DIEM;
    }

    public int getXEPLOAI() {
        return XEPLOAI;
    }

    public void setXEPLOAI(int XEPLOAI) {
        this.XEPLOAI = XEPLOAI;
    }
}
