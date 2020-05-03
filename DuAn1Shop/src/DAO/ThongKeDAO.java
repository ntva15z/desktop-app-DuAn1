/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 *
 * @author DELL
 */
public class ThongKeDAO {

    public List<Object[]> getTTphieunhap(int year) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_phieunhap(?)}";
                rs = JdbcHelper.executeQuery(sql, year);
                while (rs.next()) {
                    System.out.println("as");
                    Object[] model = {rs.getInt("thang"),
                        rs.getInt("tongsoluong"),
                        rs.getFloat("tongtien")

                    };
                    list.add(model);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object> getNamPN() {
        List<Object> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_namPN}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {

                    list.add(rs.getInt(1));
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object[]> getTTphieunhapChiiet(int year, int month) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_phieunhapct(?,?)}";
                rs = JdbcHelper.executeQuery(sql, year, month);
                while (rs.next()) {

                    Object[] model = {rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6)

                    };
                    list.add(model);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    ///////////bán hàng//////////////
    public List<Object[]> getTThoadon(int year) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_hoadon(?)}";
                rs = JdbcHelper.executeQuery(sql, year);
                while (rs.next()) {

                    Object[] model = {rs.getInt("thang"),
                        rs.getInt("soluongbanra"),
                        rs.getFloat("tienthuve"),
                        rs.getFloat("giamgia")

                    };
                    list.add(model);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object> getNamHD() {
        List<Object> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_namhd}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {

                    list.add(rs.getInt(1));
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object[]> getTThoadonChiiet(int year, int month) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_thongke_hoadonct(?,?)}";
                rs = JdbcHelper.executeQuery(sql, year, month);
                while (rs.next()) {

                    Object[] model = {rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getFloat(5),
                        rs.getFloat(6)

                    };
                    list.add(model);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    
}
