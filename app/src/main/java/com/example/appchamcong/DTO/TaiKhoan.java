package com.example.appchamcong.DTO;

public class TaiKhoan {
    int MATK,QUYEN,SDT,CHUCVU,PHONGBAN,TINHTRANG;
    String TENTK,TENNGUOIDUNG,MATKHAU,DIACHI;
    byte[] HINHANH;

    public TaiKhoan(){
        this.MATK = -1;
    }

    public TaiKhoan(int MATK, String TENTK, String TENNGUOIDUNG, String MATKHAU, byte[] HINHANH, int SDT,
                    String DIACHI, int QUYEN, int CHUCVU, int PHONGBAN, int TINHTRANG) {
        this.MATK = MATK;
        this.TENTK = TENTK;
        this.TENNGUOIDUNG = TENNGUOIDUNG;
        this.MATKHAU = MATKHAU;
        this.HINHANH = HINHANH;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.QUYEN = QUYEN;
        this.CHUCVU = CHUCVU;
        this.PHONGBAN = PHONGBAN;
        this.TINHTRANG = TINHTRANG;
    }


    public int getMATK() {
        return MATK;
    }

    public void setMATK(int MATK) {
        this.MATK = MATK;
    }

    public String getTENTK() {
        return TENTK;
    }

    public void setTENTK(String TENTK) {
        this.TENTK = TENTK;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public int getQUYEN() {
        return QUYEN;
    }

    public void setQUYEN(int QUYEN) {
        this.QUYEN = QUYEN;
    }

    public byte[] getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(byte[] HINHANH) {
        this.HINHANH = HINHANH;
    }

    public String getTENNGUOIDUNG() {
        return TENNGUOIDUNG;
    }

    public void setTENNGUOIDUNG(String TENNGUOIDUNG) {
        this.TENNGUOIDUNG = TENNGUOIDUNG;
    }

    public int getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(int CHUCVU) {
        this.CHUCVU = CHUCVU;
    }

    public int getPHONGBAN() {
        return PHONGBAN;
    }

    public void setPHONGBAN(int PHONGBAN) {
        this.PHONGBAN = PHONGBAN;
    }

    public int getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(int TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }
}
