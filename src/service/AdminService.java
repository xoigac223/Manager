package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService extends Database {

    public AdminService(){
        super();
    }

    public boolean isExists(String userName, String passWord) throws SQLException {
        String sql = "Select * from users where username = ? and passwd = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, passWord);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) return true;
        preparedStatement.close();
        return false;
    }
}
