package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminTable extends Database{

    public AdminTable(){
        super();
    }

    public boolean isExists(String userName, String passWord) throws SQLException {
        String sql = "Select * from admin where username = ? and password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, passWord);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) return true;
        preparedStatement.close();
        return false;
    }
}
