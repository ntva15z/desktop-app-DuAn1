/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.PhieuNhapDAO;
import DAO.SanPhamDAO;
import Model.HoaDon;
import Model.NhanVien;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class UtilityHelper {
    //check số lượng không được quá số lượng sản phẩm
    public static boolean checkSL(JTextField b,JTextField c,String mss){
        SanPhamDAO dao = new SanPhamDAO();
        int sl = dao.selectSLSP(b.getText());
        if(Integer.parseInt(c.getText())>sl){
             DialogHelper.alert(c, mss +" vượt quá số lượng hàng trong kho!");
             return false;
        }else{
             return true;
        }  
    }

    public String getRank(int i) {
        if (i == 0) {
            return "Thường";
        } else if (i == 1) {
            return "Bạc";
        } else if (i == 2) {
            return "Vàng";
        } else {
            return "Bạc Kim";
        }

    }

    public int setRank(String i) {
        if (i.equals("Thường")) {
            return 0;
        } else if (i.equals("Bạc")) {
            return 1;
        } else if (i.equals("Vàng")) {
            return 2;
        } else {
            return 3;
        }

    }

    /// Check trống 
    public static boolean checkNull(JTextField txt, String mess, Component c) {
        if (txt.getText().trim().length() == 0) {
            txt.requestFocus();
            DialogHelper.alert(c, mess + " không được để trống");
            return false;
        }
        return true;
    }

    public static boolean checkNull(JTextArea txt, String mess, Component c) {
        if (txt.getText().trim().length() == 0) {
            txt.requestFocus();
            DialogHelper.alert(c, mess + " không được để trống");
            return false;
        }
        return true;
    }

    public static boolean checkNull(JPasswordField txt, String mess, Component c) {
        String text = new String(txt.getPassword());
        if (text.trim().length() == 0) {
            txt.requestFocus();
            DialogHelper.alert(c, mess + " không được để trống");
            return false;
        }
        return true;
    }

    public static boolean checkSokitu(JTextField txt, String mess, int number, Component c) {
        if (txt.getText().trim().length() < number) {
            DialogHelper.alert(c, "Độ dài " + mess + " phải bằng hoặc lớn hơn " + String.valueOf(number));

            return false;
        }
        return true;
    }

    //check xóa nhân viên
   

    public static  boolean checkXoaNhanVien(String manv) {
        HoaDonDAO hddao = new HoaDonDAO();
        PhieuNhapDAO pndao = new PhieuNhapDAO();
        if (hddao.findByNhanVien(manv) == null && pndao.findByNhanVien(manv) == null) {
            return true;
        }
        return false;

    }
    //// check định dạng số với số không được phép âm

    public static boolean checkDouble(JTextField txt, Component c, String mess) {
        try {
            Double number = Double.parseDouble(txt.getText().trim());

            if (0 >= number) {
                DialogHelper.alert(c, mess + " không được âm và phải lớn hơn 0");
                txt.requestFocus();
                return false;
            }
            return true;
        } catch (Exception e) {
            DialogHelper.alert(c, mess + " phải là số");
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkInt(JTextField txt, Component c, String mess) {
        try {
            int number = Integer.parseInt(txt.getText().trim());

            if (number <= 0) {
                DialogHelper.alert(c, mess + " không được âm và phải lớn hơn 0");
                txt.requestFocus();
                return false;
            }
            return true;
        } catch (Exception e) {
            DialogHelper.alert(c, mess + " phải là số");
            txt.requestFocus();
            return false;
        }
    }

    public static boolean checkPhoneHoaDon(JTextField txt, Component c) {
        String regex = "[0-9]";
        String text = txt.getText();
        if (!text.matches(regex)) {
            DialogHelper.alert(c, "Số điện thoại chưa đúng");
            txt.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean checkPhone(JTextField txt, Component c) {
        String regex = "[0-9]{9,10}";
        String text = txt.getText();
        if (!text.matches(regex)) {
            DialogHelper.alert(c, "Số điện thoại chưa đúng");
            txt.requestFocus();
            return false;
        }
        return true;
    }
    /// Check họ tên (unicode)

    public static boolean checkName(JTextField txt, Component c) {
        String regex = "^[a-zA-Z\\s\\p{L}]+";
        String text = txt.getText().trim();
        if (!text.matches(regex)) {
            DialogHelper.alert(c, "Họ tên không được chứa bất kì kí tự đặc biệt nào");
            txt.requestFocus();
            return false;
        }
        return true;
    }
    /// Check định dạng Email

    public static boolean checkEmail(JTextField txt, Component c) {
        String regex = "\\w+@\\w+(\\.\\w+){1,2}";
        String text = txt.getText().trim();
        if (!text.matches(regex)) {
            DialogHelper.alert(c, "Email sai định dạng");
            txt.requestFocus();
            return false;

        }
        return true;
    }

    

    public static boolean checkMaKH(JTextField txt, Component c) {
        KhachHangDAO khdao = new KhachHangDAO();
        return khdao.findById(txt.getText()) == null ? true : false;

    }
    

    public static boolean checkMaNV(JTextField txt, Component c) {
        NhanVienDAO nvdao = new NhanVienDAO();
        return nvdao.findById(txt.getText()) == null ? true : false;

    }
    

    public static boolean checkMaSP(JTextField txt, Component c) {
        SanPhamDAO spdao = new SanPhamDAO();
        return spdao.findById(txt.getText()) == null ? true : false;

    }

    public static boolean checkLicense(Component c) {
        if (!ShareHelper.USER.isVaitro()) {
            DialogHelper.alert(c, "Ban không được phép sử dụng chức năng này");
            return false;
        }
        return true;
    }
  
    public static boolean checkMaPN(JTextField txt){
        PhieuNhapDAO pndao = new PhieuNhapDAO();
        return pndao.findById(txt.getText()) != null? true:false;
    }
    
    public static boolean checkMaHD(JTextField txt){
        return new HoaDonDAO().findById(txt.getText()) != null? true:false;
    }
    
    
    

}
