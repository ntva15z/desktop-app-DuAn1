/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ntva1
 */
public class NhanVien {
    private String maNV,tenNV,sdt,email,matkhau;
    private boolean vaitro = false;
    private boolean trangthai = true;
    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String sdt, String email, String matkhau) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.email = email;
        this.matkhau = matkhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public boolean isVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
    }

    @Override
    public String toString() {
        return this.tenNV;
    }
    
    
}
