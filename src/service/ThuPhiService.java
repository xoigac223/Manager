package service;

import datatable.ThongKeHoKhauData;
import model.KhoanThuModel;
import model.ThuPhiModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThuPhiService extends Database{
    public ThuPhiService(){
        super();
    }

    public int addThuPhi(ThuPhiModel thuPhiModel) throws SQLException {

        String query = "INSERT IGNORE INTO thu_phi (idHoKhau, idKhoanThu, soTienNop, thoiGianNop)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, thuPhiModel.getIdHoKhau());
        preparedStatement.setInt(2, thuPhiModel.getIdKhoanThu());
        preparedStatement.setDouble(3, thuPhiModel.getSoTienNop());
        preparedStatement.setDate(4, thuPhiModel.getThoiGianNop());
        return preparedStatement.executeUpdate();
    }

    public HashMap<String, Integer> getTop3() throws SQLException {
        HashMap rsMap = new HashMap();

        String query = "SELECT kt.tenKhoanThu, sum(soTienNop) AS tongSoTien\n" +
                "FROM thu_phi tp, khoan_thu kt\n" +
                "WHERE tp.idKhoanThu = kt.id\n" +
                "GROUP BY tp.idKhoanThu\n" +
                "ORDER BY sum(soTienNop) DESC\n" +
                "LIMIT 3";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next())
        {
            String tenKhoanThu = rs.getString("tenKhoanThu");
            Integer tongSoTien = rs.getInt("tongSoTien");
            rsMap.put(tenKhoanThu, tongSoTien);
        }
        st.close();
        return rsMap;
    }
}
