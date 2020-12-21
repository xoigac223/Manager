package service;

import datatable.KhoanThuData;
import datatable.ThuPhiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.KhoanThuModel;

import java.sql.*;

public class KhoanThuService extends Database {

    public KhoanThuService(){
        super();
    }

    public ObservableList<KhoanThuData> getAllKhoanThu()throws SQLException {
        ObservableList<KhoanThuData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from khoan_thu";
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String tenKhoanThu = rs.getString("tenKhoanThu");
            String batBuoc = rs.getString("batBuoc");
            String soTien= rs.getString("soTien");
            String thoiGian = rs.getString("thoiGian");
            String hanNop = rs.getString("hanNop");
            data.add(new KhoanThuData(String.valueOf(id), String.valueOf(i), tenKhoanThu, batBuoc, soTien, thoiGian, hanNop));
            i++;
        }
        st.close();
        return data;
    }

    public int addKhoanThu(KhoanThuModel khoanThuModel) throws SQLException{
        String query = "INSERT INTO khoan_thu (tenKhoanThu, batBuoc, soTien, thoiGian, hanNop)"
                + " values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, khoanThuModel.getTenKhoanThu());
        int batbuoc;
        if (khoanThuModel.isBatBuoc()) batbuoc = 1;
        else batbuoc = 0;
        preparedStatement.setInt(2, batbuoc);
        preparedStatement.setDouble(3, khoanThuModel.getSoTien());
        preparedStatement.setDate(4, khoanThuModel.getThoiGian());
        preparedStatement.setDate(5, khoanThuModel.getHanNop());
        return preparedStatement.executeUpdate();
    }

    public ObservableList<KhoanThuData> filterDate(String date, String searchText, String loaiKhoanThu) throws SQLException {
        ObservableList<KhoanThuData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from khoan_thu where tenKhoanThu LIKE '%" + searchText + "%' AND thoiGian = '" + date + "'";
        if (loaiKhoanThu.equals("Bắt buộc")){
            sql = sql + " AND batBuoc = 1";
        } else if (loaiKhoanThu.equals("Không bắt buộc")){
            sql = sql + " AND batBuoc = 0";
        }
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String tenKhoanThu = rs.getString("tenKhoanThu");
            String batBuoc = rs.getString("batBuoc");
            String soTien= rs.getString("soTien");
            String thoiGian = rs.getString("thoiGian");
            String hanNop = rs.getString("hanNop");
            data.add(new KhoanThuData(String.valueOf(id), String.valueOf(i), tenKhoanThu, batBuoc, soTien, thoiGian, hanNop));
            i++;
        }
        st.close();
        return data;
    }

    public ObservableList<KhoanThuData> filterMonth(String month, String year,  String searchText, String loaiKhoanThu) throws SQLException {
        ObservableList<KhoanThuData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from khoan_thu where tenKhoanThu LIKE '%" + searchText + "%'";
        if (loaiKhoanThu.equals("Bắt buộc")){
            sql = sql + " AND batBuoc = 1";
        } else if (loaiKhoanThu.equals("Không bắt buộc")){
            sql = sql + " AND batBuoc = 0";
        }
        if (!month.equals("Tất cả")){
            sql = sql + " AND DATE_FORMAT(thoiGian,'%M/%Y')='"+month+"/"+year+"'";
        } else {
            sql = sql + " AND DATE_FORMAT(thoiGian, '%Y')='" + year + "'";
        }
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String tenKhoanThu = rs.getString("tenKhoanThu");
            String batBuoc = rs.getString("batBuoc");
            String soTien= rs.getString("soTien");
            String thoiGian = rs.getString("thoiGian");
            String hanNop = rs.getString("hanNop");
            data.add(new KhoanThuData(String.valueOf(id), String.valueOf(i), tenKhoanThu, batBuoc, soTien, thoiGian, hanNop));
            i++;
        }
        st.close();
        return data;
    }

    public ObservableList<KhoanThuData> filterAll(String searchText, String loaiKhoanThu) throws SQLException {
        ObservableList<KhoanThuData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from khoan_thu where tenKhoanThu LIKE '%" + searchText + "%'";
        if (loaiKhoanThu.equals("Bắt buộc")){
            sql = sql + " AND batBuoc = 1";
        } else if (loaiKhoanThu.equals("Không bắt buộc")){
            sql = sql + " AND batBuoc = 0";
        }
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String tenKhoanThu = rs.getString("tenKhoanThu");
            String batBuoc = rs.getString("batBuoc");
            String soTien= rs.getString("soTien");
            String thoiGian = rs.getString("thoiGian");
            String hanNop = rs.getString("hanNop");
            data.add(new KhoanThuData(String.valueOf(id), String.valueOf(i), tenKhoanThu, batBuoc, soTien, thoiGian, hanNop));
            i++;
        }
        st.close();
        return data;
    }

    public ObservableList<ThuPhiData> getThuPhi(String id, String soTien)throws SQLException {
        ObservableList<ThuPhiData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "SELECT hk.id AS idHoKhau, hoTen, count(tv.idNhanKhau) AS soThanhVien,  diaChiHienNay, thoiGianNop, soTienNop\n" +
                "FROM ho_khau hk INNER JOIN nhan_khau nk ON (hk.idChuHo = nk.ID) INNER JOIN thanh_vien_cua_ho tv ON (hk.ID = tv.idHoKhau)\n" +
                "LEFT OUTER JOIN thu_phi tp ON (hk.ID = tp.idHoKhau AND tp.idKhoanThu = " + id + ")\n" +
                "GROUP BY hoTen, thoiGianNop, diaChiHienNay, hk.id";
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            String idHoKhau = rs.getString("idHoKhau");
            String hoTenChuHo = rs.getString("hoTen");
            String diaChiHienNay = rs.getString("diaChiHienNay");
            int soThanhVien = rs.getInt(3);
            String thoiGianNop = rs.getString("thoiGianNop");
            Integer soTienPhaiNop = Integer.valueOf(soTien) * soThanhVien * 12;
            Double soTienNop = rs.getDouble("soTienNop");
            data.add(new ThuPhiData(idHoKhau, String.valueOf(i), hoTenChuHo, String.valueOf(soThanhVien), String.valueOf(soTienPhaiNop), diaChiHienNay, thoiGianNop, soTienNop));
            i++;
        }
        st.close();
        return data;
    }

    public int tinhTongSoTien(String idKhoanThu) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "SELECT SUM(tp.soTienNop) AS tongSoTienNop\n" +
                "FROM thu_phi tp\n" +
                "WHERE tp.idKhoanThu = "+idKhoanThu+"\n" +
                "GROUP BY tp.idKhoanThu";
        ResultSet rs = st.executeQuery(sql);
        int tongSoTien = 0;
        while (rs.next()) {
            tongSoTien = rs.getInt("tongSoTienNop");
        }
        return tongSoTien;
    }

    public int tinhSoNguoiDaNop(String idKhoanThu) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "SELECT count(*) AS soNguoiDaNop\n" +
                "FROM thu_phi tp\n" +
                "WHERE tp.idKhoanThu = "+idKhoanThu+"\n" +
                "GROUP BY tp.idKhoanThu";
        ResultSet rs = st.executeQuery(sql);
        int soNguoiDaNop = 0;
        while (rs.next()) {
            soNguoiDaNop = rs.getInt("soNguoiDaNop");
        }
        return soNguoiDaNop;
    }
}
