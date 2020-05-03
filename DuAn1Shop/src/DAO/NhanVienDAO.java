/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntva1
 */
public class NhanVienDAO {
    public void insert(NhanVien model){
        String sql = "INSERT INTO NHANVIEN VALUES(?,?,?,?,?,0,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNV(),
                model.getTenNV(),
                model.getSdt(),
                model.getEmail(),
                model.getMatkhau(),
                model.isVaitro());        
    }
    
    public void update(NhanVien model){
        String sql = "UPDATE NHANVIEN SET TENNV=?,SDT=?,EMAIL=?,MATKHAU=?,VAITRO=? WHERE MANV=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenNV(),
                model.getSdt(),
                model.getEmail(),
                model.getMatkhau(),
                model.isVaitro(),
                model.getMaNV());
    }
       public void active(NhanVien model, boolean trangthai){
        String sql = "UPDATE NHANVIEN SET TRANGTHAI= ? WHERE MANV=?";
        JdbcHelper.executeUpdate(sql,
                 trangthai,
                model.getMaNV());
    }
    
    public void delete(String MaNV){
        String sql = "DELETE FROM NHANVIEN WHERE MANV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
    
    public List<NhanVien> select(){
        String sql = "SELECT * FROM NHANVIEN";
        return select(sql);    
    } 
    
    public NhanVien findById(String manv){
        String sql  = "SELECT * FROM NHANVIEN WHERE MANV=?";
        List<NhanVien> list = select(sql,manv);
        return list.size()>0 ? list.get(0) : null;
    } 
    
    private List<NhanVien> select(String sql,Object...args){
        List<NhanVien> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                NhanVien model = readFromResultSet(rs);
                list.add(model);
            }
            rs.getStatement().getConnection().close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    private NhanVien readFromResultSet(ResultSet rs) throws SQLException{
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MANV"));
        model.setTenNV(rs.getString("TENNV"));
        model.setSdt(rs.getString("SDT"));
        model.setEmail(rs.getString("EMAIL"));
        model.setMatkhau(rs.getString("MATKHAU"));
        model.setVaitro(rs.getBoolean("VAITRO"));
        model.setTrangthai(rs.getBoolean("TRANGTHAI"));
        return model;
        
    }
}
