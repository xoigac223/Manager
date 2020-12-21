package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoKhauService extends Database{
    public HoKhauService(){
        super();
    }

    public int tinhSoHoKhau() throws SQLException {
        Statement st = conn.createStatement();
        String sql = "SELECT COUNT(*) AS soHoKhau\n" +
                "FROM ho_khau";
        ResultSet rs = st.executeQuery(sql);
        int soHoKhau = 0;
        while (rs.next()) {
            soHoKhau = rs.getInt("soHoKhau");
        }
        return soHoKhau;
    }

}
