/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import User.User;

import java.sql.*;

/**
 *
 * @author daonv
 */
public class UsersTable {
    static private Connection conn;
    
    public static void connection() throws ClassNotFoundException,
          SQLException{
        conn = ConnectionUtils.getMyConnection();
    }
    
   
    public static int addUser(String userName, String passwd) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("insert into users (userName, passwd)" + " values (?, ?)");
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,passwd);
        return preparedStatement.executeUpdate();
    }
    
    public static void printAllUsers()throws SQLException{
        Statement st = conn.createStatement();
        String sql = "select * from users";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
        {
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String passwd = rs.getString("passwd");
        System.out.printf("%s, %s, %s\n", id, userName, passwd);
        }
        st.close();
    }
    
    public static int deleteById(int id)throws SQLException{
        String sql = "Delete from users where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        preparedStatement.close();
        return rs;
    }

    public static User findById(int id) throws SQLException{
        String sql = "Select * from users where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String userName = rs.getString("userName");
            String passwd = rs.getString("passwd");
            return new User(userName, passwd);
        }
        preparedStatement.close();
        return null;
    }

    public static int upDateById(int id, String userName, String passwd) throws SQLException{
        String sql = "Update users Set userName = ?, passwd = ? Where id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,passwd);
        preparedStatement.setInt(3,id);
        int rs = preparedStatement.executeUpdate();
        preparedStatement.close();
        return rs;
    }

}
