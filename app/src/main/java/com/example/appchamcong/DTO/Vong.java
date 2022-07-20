package com.example.appchamcong.DTO;

public class Vong {
    int IDTK;
    float VONG;
    int SOCAU;

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public float getVONG() {
        return VONG;
    }

    public void setVONG(float VONG) {
        this.VONG = VONG;
    }

    public int getSOCAU() {
        return SOCAU;
    }

    public void setSOCAU(int SOCAU) {
        this.SOCAU = SOCAU;
    }

    public Vong(int IDTK, float VONG, int SOCAU) {
        this.IDTK = IDTK;
        this.VONG = VONG;
        this.SOCAU = SOCAU;
    }
    public Vong(float VONG, int SOCAU) {
        this.VONG = VONG;
        this.SOCAU = SOCAU;
    }
}
