/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.PhieuNhap;
import Model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class PhieuNhapDAO {
   public void InsertPhieuNhap(PhieuNhap model){
       String SQL = "insert into PHIEUNHAP values(?,?,?,?)";
       JdbcHelper.executeUpdate( SQL, model.getMaPN(), model.getMaNVPN(),model.getNgayNhap(), model.getGhiChu());
   } 
   
   public List<PhieuNhap> select() {
        String sql = "SELECT * FROM PHIEUNHAP";
        return select(sql);
    }

    public PhieuNhap findById(String masp) {
        String sql = "SELECT * FROM PHIEUNHAP WHERE MAPHIEU = ?";
        List<PhieuNhap> list = select(sql, masp);
        return list.size() > 0 ?list.get(0):null;
    }
    public List<PhieuNhap> selectByKeyword(String keyword){
        String sql = "SELECT * FROM PHIEUNHAP WHERE MAPHIEU LIKE ?";
        return select(sql,"%"+keyword+"%");
    }
    
   
    
    
    public List<PhieuNhap> select(String sql,Object...args){
        List<PhieuNhap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                System.out.println("ad");
                rs = JdbcHelper.executeQuery(sql, args);
                System.out.println(rs+"dâd");
                while (rs.next()) {                    
                    PhieuNhap model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
    
    public PhieuNhap readFromResultSet(ResultSet rs) throws SQLException{
        PhieuNhap model = new PhieuNhap();
        model.setMaPN(rs.getString("MAPHIEU"));
        model.setMaNVPN(rs.getString("MANV"));
        model.setNgayNhap(rs.getDate("NGAYNHAP"));
        model.setGhiChu(rs.getString("GHICHU"));
        return model;
    }
      public PhieuNhap findByNhanVien(String manv){
        String sql  = "SELECT * FROM PHIEUNHAP WHERE MANV=?";
        List<PhieuNhap> list = select(sql,manv);
        return list.size()>0 ? list.get(0) : null;
    }
}
