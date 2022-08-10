package com.example.appchamcong.DTO;

public class GopY {
    int IDGY;
    String TENNGUOIDUNG;
    int SDT;
    String NOIDUNG;
    byte[] HINHANH;


    public GopY(int IDGY, String TENNGUOIDUNG, byte[] HINHANH, int SDT, String NOIDUNG) {
        this.IDGY = IDGY;
        this.TENNGUOIDUNG = TENNGUOIDUNG;
        this.HINHANH = HINHANH;
        this.SDT = SDT;
        this.NOIDUNG = NOIDUNG;
    }

    public int getIDGY() {
        return IDGY;
    }

    public void setIDGY(int IDGY) {
        this.IDGY = IDGY;
    }

    public String getTENNGUOIDUNG() {
        return TENNGUOIDUNG;
    }

    public void setTENNGUOIDUNG(String TENTK) {
        this.TENNGUOIDUNG = TENTK;
    }

    public byte[] getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(byte[] HINHANH) {
        this.HINHANH = HINHANH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getNOIDUNG() {
        return NOIDUNG;
    }

    public void setNOIDUNG(String NOIDUNG) {
        this.NOIDUNG = NOIDUNG;
    }
}
