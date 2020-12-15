package service;

import datatable.KhoanThuData;
import datatable.ThuPhiData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import datatable.NhanKhauData;
import model.KhoanThuModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        System.out.println(id + " " + soTien);
        ObservableList<ThuPhiData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "SELECT hoTen, count(tv.idNhanKhau) AS soThanhVien,  diaChiHienNay, thoiGianNop\n" +
                "FROM ho_khau hk INNER JOIN nhan_khau nk ON (hk.idChuHo = nk.ID) INNER JOIN thanh_vien_cua_ho tv ON (hk.ID = tv.idHoKhau)\n" +
                "LEFT OUTER JOIN thu_phi tp ON (hk.ID = tp.idHoKhau AND tp.idKhoanThu = " + id + ")\n" +
                "GROUP BY hoTen, thoiGianNop, diaChiHienNay";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            String hoTenChuHo = rs.getString("hoTen");
            String soTienDaNop = rs.getString("diaChiHienNay");
            int soThanhVien = rs.getInt(2);
            String thoiGianNop = rs.getString("thoiGianNop");
            Integer soTienPhaiNop = Integer.valueOf(soTien) * soThanhVien;
            data.add(new ThuPhiData(String.valueOf(i), hoTenChuHo, String.valueOf(soThanhVien), String.valueOf(soTienPhaiNop), soTienDaNop, thoiGianNop));
            i++;
        }
        st.close();
        return data;
    }
}
