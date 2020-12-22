package service;

import datatable.KhoanThuData;
import datatable.ThongKeData;
import datatable.ThongKeHoKhauData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThongKeService extends Database{
    public ThongKeService(){
        super();
    }


    public ObservableList<ThongKeData> filter(String searchText) throws SQLException {
        ObservableList<ThongKeData> data = FXCollections.observableArrayList();

        Statement st = conn.createStatement();
        String sql = "SELECT hk.id, hoTen, diaChiHienNay, sum(tp.soTienNop) AS soTienNop\n" +
                "FROM ho_khau hk INNER JOIN nhan_khau nk ON (hk.idChuHo = nk.ID AND nk.hoTen LIKE '%" + searchText +"%') LEFT OUTER JOIN  thu_phi tp ON (hk.ID = tp.idHoKhau) INNER JOIN khoan_thu kt ON(tp.idKhoanThu = kt.ID AND kt.batBuoc = 0)\n" +
                "GROUP BY hk.ID";
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String hoTenChuHo = rs.getString("hoTen");
            String diaChiHienNay = rs.getString("diaChiHienNay");
            String tongSoTien= rs.getString("soTienNop");
            data.add(new ThongKeData(String.valueOf(id), String.valueOf(i), hoTenChuHo, diaChiHienNay, tongSoTien));
            i++;
        }
        st.close();
        return data;
    }

    public ObservableList<ThongKeHoKhauData> getThongKeTheoHoKhau(String idHoKhau) throws SQLException {
        ObservableList<ThongKeHoKhauData> data = FXCollections.observableArrayList();

        Statement st = conn.createStatement();
        String sql = "SELECT kt.tenKhoanThu, tp.thoiGianNop, tp.soTienNop\n" +
                "FROM thu_phi tp, khoan_thu kt\n" +
                "WHERE tp.idKhoanTHu = kt.ID AND kt.batBuoc = 0 AND tp.idHoKhau = " + idHoKhau;
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
        {
            String tenKhoanThu = rs.getString("tenKhoanThu");
            String ngayNop = rs.getString("thoiGianNop");
            String soTienNop = rs.getString("soTienNop");
            data.add(new ThongKeHoKhauData(tenKhoanThu, ngayNop, soTienNop));
        }
        st.close();
        return data;
    }

}
