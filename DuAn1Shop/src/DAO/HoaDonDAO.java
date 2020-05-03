/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HoaDonDAO {
    public void insert(HoaDon model){
      String sql = "INSERT INTO HOADON VALUES(?,?,?,?,?,?,?)"; 
        JdbcHelper.executeUpdate(sql,
                model.getMaHD(),
                model.getMaNV(),
                model.getMaKH(),
                model.getNgayBan(),
                model.getGiamGia(),
                model.getTongTien(),
                model.getGhiChu());
    }
    public void update(HoaDon model){
        String sql = "UPDATE HOADON SET MANV=?, MAKH=?, NGAYBAN=?, GIAMGIA=?, TONGTIEN=?,GHICHU=? WHERE MAHD=?";
        JdbcHelper.executeUpdate(sql,  
                model.getMaNV(),
                model.getMaKH(),
                model.getNgayBan(),
                model.getGiamGia(),
                model.getTongTien(),
                model.getGhiChu(),
                model.getMaHD());
    }
    
    public void delete(String MaHD){
        String sql = "DELETE FROM HOADON WHERE MAHD=?";
        JdbcHelper.executeUpdate(sql, MaHD);
    }
    
    public List<HoaDon> select(){
        String sql = "SELECT * FROM HOADON";
        return select(sql);    
    } 

    public HoaDon findById(String makh){
        String sql  = "SELECT * FROM HOADON WHERE MAHD=?";
        List<HoaDon> list = select(sql,makh);
        return list.size()>0 ? list.get(0) : null;
    } 
    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HOADON WHERE MAHD LIKE ? or MAKH like ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
    
    private List<HoaDon> select(String sql,Object...args){
        List<HoaDon> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                HoaDon model = readFromResultSet(rs);
                list.add(model);
            }
            rs.getStatement().getConnection().close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    private HoaDon readFromResultSet(ResultSet rs) throws SQLException{
        HoaDon model = new HoaDon();
        model.setMaHD(rs.getString("MAHD"));
        model.setMaNV(rs.getString("MANV"));
        model.setMaKH(rs.getString("MAKH"));
        model.setNgayBan(rs.getDate("NGAYBAN"));
        model.setGiamGia(rs.getInt("GIAMGIA"));
        model.setTongTien(rs.getFloat("TONGTIEN"));
        model.setGhiChu(rs.getString("GHICHU"));
       
        return model;
        
    }
    
    public float selectTongTien(String mahd){
        float tongtien =0;
        String sql  = "SELECT *FROM HOADON WHERE MAHD =?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql,mahd);
            while(rs.next()){
                 tongtien=rs.getFloat("TONGTIEN");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tongtien;
    }
    public float selectTongTien_MaKH(String tenkh){
        float tongtien =0;
        String sql  = "SELECT SUM(TONGTIEN)as result  FROM dbo.HOADON INNER JOIN dbo.KHACHHANG ON KHACHHANG.MAKH = HOADON.MAKH WHERE TENKH= "+ "N'" + tenkh + "'";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while(rs.next()){
                 tongtien=rs.getFloat("result");
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tongtien;
    }
    ///Đại///
     public HoaDon findByNhanVien(String manv){
        String sql  = "SELECT * FROM HOADON WHERE MANV=?";
        List<HoaDon> list = select(sql,manv);
        return list.size()>0 ? list.get(0) : null;
    }
}
