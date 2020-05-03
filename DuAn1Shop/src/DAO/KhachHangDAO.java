/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.KhachHang;
import Model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author DELL
 */
public class KhachHangDAO {
    public void insert(KhachHang model){
        String sql = "INSERT INTO KhachHang VALUES(?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaKH(),
                model.getTenKH(),
                model.isGioitinh(),
                model.getDiachi(),
                model.getSdt(),
                model.getEmail(),
                model.getLoai(),
                model.getGhichu());
              
        
    }
    
    public void update(KhachHang model){
        String sql = "UPDATE KhachHang SET TENKH=?, GIOITINH=?, DIACHI=?, SDT=?, EMAIL=?,LOAI=?,GHICHU=? WHERE MAKH=?";
        JdbcHelper.executeUpdate(sql,  
                model.getTenKH(),
                model.isGioitinh(),
                model.getDiachi(),
                model.getSdt(),
                model.getEmail(),
                model.getLoai(),
                model.getGhichu(),
                model.getMaKH());
    }
    
    public void delete(String MaKH){
        String sql = "DELETE FROM KHACHHANG WHERE MAKH=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }
    
    public List<KhachHang> select(){
        String sql = "SELECT * FROM KHACHHANG";
       
        return select(sql);    
    } 
    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KHACHHANG WHERE TENKH LIKE ? or MAKH like ?";
        return select(sql, "%" + keyword + "%",
                       "%" + keyword + "%");
    }
    
    
    
    public KhachHang findById(String makh){
        String sql  = "SELECT * FROM KHACHHANG WHERE MAKH=?";
        List<KhachHang> list = select(sql,makh);
        return list.size()>0 ? list.get(0) : null;
    } 
    
    private List<KhachHang> select(String sql,Object...args){
        List<KhachHang> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                KhachHang model = readFromResultSet(rs);
                list.add(model);
            }
            rs.getStatement().getConnection().close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    private KhachHang readFromResultSet(ResultSet rs) throws SQLException{
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("MAKH"));
        model.setTenKH(rs.getString("TENKH"));
        model.setGioitinh(rs.getBoolean("GIOITINH"));
        model.setDiachi(rs.getString("DIACHI"));
        model.setSdt(rs.getString("SDT"));
        model.setEmail(rs.getString("EMAIL"));
        model.setLoai(rs.getInt("LOAI"));
        model.setGhichu(rs.getString("GHICHU"));
       
        return model;
        
    }
    //// Hoàng//////
      public DefaultComboBoxModel LayDuLieucbb() {
        String cautruyvan = "select *from khachhang";
        ResultSet rs = JdbcHelper.executeQuery(cautruyvan);
        DefaultComboBoxModel cbbmodel = new DefaultComboBoxModel();
        try {
            while (rs.next()) {
                String makh = rs.getString("MAKH");
                cbbmodel.addElement(makh);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cbbmodel;
    }
     public int selectLoaiKH(String tenkh){
        int loai =0;
        String sql  = "SELECT *FROM KHACHHANG WHERE TENKH LIKE N'%"+tenkh+"%'";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql);
            while(rs.next()){
                 loai=rs.getInt("LOAI");              
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loai;
    }
  
     public List<KhachHang> selectKHBySDT(String sdt) {
        String sql = "SELECT * FROM KHACHHANG WHERE SDT=? ";
        return select(sql,  sdt );
    }
}
