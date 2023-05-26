package com.example.manthilan_qly_vattu;

public class manlan_VatTu {
    private String mamt,tenmt,loaimt,hangsx;
    private int namsx,dongia,soluong;

    public manlan_VatTu(String mamt, String tenmt, String loaimt, String hangsx, int namsx, int dongia, int soluong) {
        this.mamt = mamt;
        this.tenmt = tenmt;
        this.loaimt = loaimt;
        this.hangsx = hangsx;
        this.namsx = namsx;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public manlan_VatTu() {
    }

    public manlan_VatTu(String tenmt, String loaimt, String hangsx, int namsx, int dongia, int soluong) {
        this.tenmt = tenmt;
        this.loaimt = loaimt;
        this.hangsx = hangsx;
        this.namsx = namsx;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public String getMamt() {
        return mamt;
    }

    public void setMamt(String mamt) {
        this.mamt = mamt;
    }

    public String getTenmt() {
        return tenmt;
    }

    public void setTenmt(String tenmt) {
        this.tenmt = tenmt;
    }

    public String getLoaimt() {
        return loaimt;
    }

    public void setLoaimt(String loaimt) {
        this.loaimt = loaimt;
    }

    public String getHangsx() {
        return hangsx;
    }

    public void setHangsx(String hangsx) {
        this.hangsx = hangsx;
    }

    public int getNamsx() {
        return namsx;
    }

    public void setNamsx(int namsx) {
        this.namsx = namsx;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
