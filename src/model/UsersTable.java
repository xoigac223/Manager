/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 *
 * @author daonv
 */
public class UsersTable extends Database{

    public UsersTable(){
        super();
    }

    public int addUser(String userName, String passwd) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("insert into users (userName, passwd)" + " values (?, ?)");
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,passwd);
        return preparedStatement.executeUpdate();
    }
    
    public ObservableList<UserData> getAllUsers()throws SQLException{
        ObservableList<UserData> data = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String sql = "select * from users";
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        while (rs.next())
        {
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String passwd = rs.getString("passwd");
            data.add(new UserData(String.valueOf(id),String.valueOf(i) ,userName, passwd,"des"));
            i++;
        }
        st.close();
        return data;
    }
    
    public int deleteById(int id)throws SQLException{
        String sql = "Delete from users where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        preparedStatement.close();
        return rs;
    }

    public User findById(int id) throws SQLException{
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

    public int upDateById(int id, String userName, String passwd) throws SQLException{
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
