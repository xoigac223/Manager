package service;

import model.KhoanThuModel;
import model.ThuPhiModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThuPhiService extends Database{
    public ThuPhiService(){
        super();
    }

    public int addThuPhi(ThuPhiModel thuPhiModel) throws SQLException {

        String query = "INSERT IGNORE INTO thu_phi (idHoKhau, idKhoanThu, soTienNop, thoiGianNop)"
                + " values (?, ?, ?, ?)";
        System.out.println(query);
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, thuPhiModel.getIdHoKhau());
        preparedStatement.setInt(2, thuPhiModel.getIdKhoanThu());
        preparedStatement.setDouble(3, thuPhiModel.getSoTienNop());
        preparedStatement.setDate(4, thuPhiModel.getThoiGianNop());
        return preparedStatement.executeUpdate();
    }
}
