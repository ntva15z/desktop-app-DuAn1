/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.PhieuNhapCT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class PhieuNhapCTDAO {
       public void InsertPhieuNhapCT(PhieuNhapCT model){
       String SQL = "INSERT INTO PHIEUNHAPCT VALUES(?,?,?,?)";
       JdbcHelper.executeUpdate( SQL, model.getMaPN(), model.getMaSP(),model.getSL(), model.getDongia());
   } 
   
   public List<PhieuNhapCT> select() {
        String sql = "SELECT * FROM PHIEUNHAPCT";
        return select(sql);
    }

    public List<PhieuNhapCT> findById(String mapn) {
        String sql = "SELECT * FROM PHIEUNHAPCT WHERE MAPHIEU = ?";
        List<PhieuNhapCT> list = select(sql, mapn);
        return list;
    }

    
    
    public List<PhieuNhapCT> select(String sql,Object...args){
        List<PhieuNhapCT> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    PhieuNhapCT model = readFromResultSet(rs);
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
    
    public PhieuNhapCT readFromResultSet(ResultSet rs) throws SQLException{
        PhieuNhapCT model = new PhieuNhapCT();
        model.setMaPN(rs.getString("MAPHIEU"));
        model.setMaSP(rs.getString("MASP"));
        model.setSL(rs.getInt("SOLUONG"));
        model.setDongia(rs.getFloat("DONGIA"));
        return model;
    }
}
