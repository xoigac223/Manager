package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import datatable.NhanKhauData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NhanKhauService extends Database {

    public NhanKhauService(){
        super();
    }

    public ObservableList<NhanKhauData> getAllNhanKhau()throws SQLException {
        ObservableList<NhanKhauData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from nhan_khau";
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String hoTen = rs.getString("hoTen");
            String ngaySinh = rs.getString("namSinh");
            String gioiTinh = rs.getString("gioiTinh");
            String diaChiHienNay = rs.getString("diaChiHienNay");
            data.add(new NhanKhauData(String.valueOf(id), String.valueOf(i), hoTen, ngaySinh, gioiTinh, diaChiHienNay));
            i++;
        }
        st.close();
        return data;
    }

    public int addNhanKhau(String hoTen, String bietDanh, java.sql.Date namSinh, String gioiTinh,
                           String nguyenQuan, String danToc, String tonGiao, String quocTich,
                           String soHoChieu, String noiThuongTru, String diaChiHienNay,
                           String trinhDoHocVan, String TrinhDoChuyenMon, String bietTiengDanToc,
                           String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec, java.sql.Date ngayTao,
                           int idNguoiTao) throws SQLException{
        String query = "INSERT INTO nhan_khau (hoTen, bietDanh, namSinh, gioiTinh, nguyenQuan, danToc, tonGiao, quocTich, soHoChieu, noiThuongTru, diaChiHienNay, trinhDoHocVan, TrinhDoChuyenMon, bietTiengDanToc, trinhDoNgoaiNgu, ngheNghiep, noiLamViec, ngayTao, idNguoiTao)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, hoTen);
        preparedStatement.setString(2, bietDanh);
        preparedStatement.setDate(3, namSinh);
        preparedStatement.setString(4, gioiTinh);
        preparedStatement.setString(5, nguyenQuan);
        preparedStatement.setString(6, danToc);
        preparedStatement.setString(7, tonGiao);
        preparedStatement.setString(8, quocTich);
        preparedStatement.setString(9, soHoChieu);
        preparedStatement.setString(10, noiThuongTru);
        preparedStatement.setString(11, diaChiHienNay);
        preparedStatement.setString(12, trinhDoHocVan);
        preparedStatement.setString(13, TrinhDoChuyenMon);
        preparedStatement.setString(14, bietTiengDanToc);
        preparedStatement.setString(15, trinhDoNgoaiNgu);
        preparedStatement.setString(16, ngheNghiep);
        preparedStatement.setString(17, noiLamViec);
        preparedStatement.setDate(18, ngayTao);
        preparedStatement.setInt(19, idNguoiTao);
        return preparedStatement.executeUpdate();
    }
}
