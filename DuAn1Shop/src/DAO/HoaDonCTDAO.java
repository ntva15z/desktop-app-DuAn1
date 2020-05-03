/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.HoaDonCT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ntva1
 */
public class HoaDonCTDAO {
    public void insert(HoaDonCT model){
        String sql = "INSERT INTO HOADONCT VALUES(?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaHD(),
                model.getMaSP(),
                model.getSoLuong(),
                model.getDonGia(),
                model.getTongTien());
    }
    public void update(HoaDonCT model,String mahd){
        String sql = "UPDATE HOADONCT SET MAHD="+mahd+", MASP=?, SL=?, DONGIA=? , TONGTIEN=?" ;
        JdbcHelper.executeUpdate(sql,
                model.getMaHD(),
                model.getMaSP(),
                model.getSoLuong(),
                model.getDonGia(),
                model.getTongTien());
                
    }
    
    public void delete(String MaHD){
        String sql = "DELETE FROM HOADONCT WHERE MAHD=?";
        JdbcHelper.executeUpdate(sql, MaHD);
    }
    
    public List<HoaDonCT> select(){
        String sql = "SELECT * FROM HOADONCT";
        return select(sql);    
    } 

    public HoaDonCT findById(String mahdct){
        String sql  = "SELECT * FROM HOADONCT WHERE MAHDCT=?";
        List<HoaDonCT> list = select(sql,mahdct);
        return list.size()>0 ? list.get(0) : null;
    } 
    
    private List<HoaDonCT> select(String sql,Object...args){
        List<HoaDonCT> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                HoaDonCT model = readFromResultSet(rs);
                list.add(model);
            }
            rs.getStatement().getConnection().close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    private HoaDonCT readFromResultSet(ResultSet rs) throws SQLException{
        HoaDonCT model = new HoaDonCT();
        model.setMaHDCT(rs.getInt("MAHDCT"));
        model.setMaHD(rs.getString("MAHD"));
        model.setMaSP(rs.getString("MASP"));
        model.setSoLuong(rs.getInt("SL"));
        model.setDonGia(rs.getFloat("DONGIA"));
        model.setTongTien(rs.getFloat("TONGTIEN"));
        return model;
        
    }
    
    public List<HoaDonCT> findByIdMaHD_HD(String mahd){
        List<HoaDonCT> list = new ArrayList<>();
        String sql  = "SELECT MAHDCT,HOADONCT.MAHD,MASP,SL,DONGIA,HOADONCT.TONGTIEN "
                + "FROM dbo.HOADONCT INNER JOIN dbo.HOADON ON HOADON.MAHD = HOADONCT.MAHD "
                + "WHERE HOADON.MAHD = HOADONCT.MAHD AND HOADON.MAHD =?";
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql,mahd);
            while(rs.next()){
                HoaDonCT model = new HoaDonCT();
                model.setMaHDCT(rs.getInt("MAHDCT"));
                model.setMaHD(rs.getString("MAHD"));
                model.setMaSP(rs.getString("MASP"));
                model.setSoLuong(rs.getInt("SL"));
                model.setDonGia(rs.getFloat("DONGIA"));
                model.setTongTien(rs.getFloat("TONGTIEN"));
                list.add(model);
            }
            System.out.println("thanh cong");
            
        } catch (Exception e) {
            System.out.println("that bai" +e);
        }
        return list;
    } 
}
