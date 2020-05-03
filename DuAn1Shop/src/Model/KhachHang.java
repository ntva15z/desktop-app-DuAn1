/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DELL
 */
public class KhachHang {
    private String maKH, tenKH, sdt,  email, diachi, ghichu;
    private int loai;
    private boolean gioitinh;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String sdt, String email, String diachi, String ghichu, int loai, boolean gioitinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.ghichu = ghichu;
        this.loai = loai;
        this.gioitinh = gioitinh;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String makh) {
        this.maKH = makh;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKh) {
        this.tenKH = tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }
    
}
