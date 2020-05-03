/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ntva1
 */
public class SanPhamDAO {
    public void insert(SanPham model){
        String sql = "INSERT INTO SANPHAM VALUES(?,?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaSP(),
                model.getTenSP(),
                model.getThuongHieu(),
                model.getLoaiSP(),
                model.getSoLuong(),
                model.getGiaNhap(),
                model.getGiaBan(),
                model.getHinhSP(),
                model.getGhiChu());
    }
    public void update(SanPham model){
        String sql = "UPDATE SANPHAM SET TENSP=?,THUONGHIEU=?,LOAISP=?,SOLUONG=?,GIANHAP=?,GIABAN=?,HINH=?,GHICHU=? WHERE MASP=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenSP(),
                model.getThuongHieu(),
                model.getLoaiSP(),
                model.getSoLuong(),
                model.getGiaNhap(),
                model.getGiaBan(),
                model.getHinhSP(),
                model.getGhiChu(),
                model.getMaSP());
    }
    public void delete(String masp){
        String sql = "DELETE FROM SANPHAM WHERE MASP=?";
        JdbcHelper.executeUpdate(sql, masp);
    }
    
    public List<SanPham> select() {
        String sql = "SELECT * FROM SANPHAM";
        return select(sql);
    }

    public SanPham findById(String masp) {
        String sql = "SELECT * FROM SANPHAM WHERE MASP = ?";
        List<SanPham> list = select(sql, masp);
        return list.size() > 0 ?list.get(0):null;
    }
    
    public List<SanPham> selectByKeyword(String keyword){
        String sql = "SELECT * FROM SANPHAM WHERE TENSP LIKE ? OR  MASP LIKE ?";
        return select(sql,"%"+keyword+"%","%"+keyword+"%");
    }
    
    public List<SanPham> select(String sql,Object...args){
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    SanPham model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }
    
    public SanPham readFromResultSet(ResultSet rs) throws SQLException{
        SanPham model = new SanPham();
        model.setMaSP(rs.getString("MASP"));
        model.setTenSP(rs.getString("TENSP"));
        model.setThuongHieu(rs.getString("THUONGHIEU"));
        model.setLoaiSP(rs.getString("LOAISP"));
        model.setSoLuong(rs.getInt("SOLUONG"));
        model.setGiaNhap(rs.getFloat("GIANHAP"));
        model.setGiaBan(rs.getFloat("GIABAN"));
        model.setHinhSP(rs.getString("HINH"));
        model.setGhiChu(rs.getString("GHICHU"));
        return model;
    }
      public DefaultComboBoxModel LayDuLieucbb(String name) {
        String cautruyvan = "select *from sanpham where tensp like N'%"+name+"%'";
        ResultSet rs = JdbcHelper.executeQuery(cautruyvan);
        DefaultComboBoxModel cbbmodel = new DefaultComboBoxModel();
        try {
            while (rs.next()) {
                String tensp = rs.getString("TENSP");
                cbbmodel.addElement(tensp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cbbmodel;
    }
    public float selectGiaBan(String tensp){
        float gia =0;
        String sql  = "SELECT *FROM SANPHAM WHERE TENSP LIKE N'%"+tensp+"%'";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while(rs.next()){
                 gia=rs.getFloat("GIABAN");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gia;
    }
    public String selectMaSP(String tensp){
        String masp ="";
        String sql  = "SELECT *FROM SANPHAM WHERE TENSP LIKE N'%"+tensp+"%'";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while(rs.next()){
                 masp=rs.getString("MASP");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return masp;
    }
    public int selectSLSP(String masp){
        int sl=0;
        String sql  = "SELECT *FROM SANPHAM WHERE MASP=?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql,masp);
            while(rs.next()){
                 sl=rs.getInt("SOLUONG");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
    //hoang kt
}
