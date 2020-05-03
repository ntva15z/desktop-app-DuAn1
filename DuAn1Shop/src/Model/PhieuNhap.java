/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class PhieuNhap {
    String maPN, maNVPN, GhiChu;
    Date NgayNhap;
    

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNVPN() {
        return maNVPN;
    }

    public void setMaNVPN(String maNVPN) {
        this.maNVPN = maNVPN;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public PhieuNhap(String maPN, String maNVPN, String GhiChu, Date NgayNhap) {
        this.maPN = maPN;
        this.maNVPN = maNVPN;
        this.GhiChu = GhiChu;
        this.NgayNhap = NgayNhap;
    }

    public PhieuNhap() {
    }
}
