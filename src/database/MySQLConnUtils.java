package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author daonv
 */
public class MySQLConnUtils {

 public static Connection getMySQLConnection() throws SQLException,
         ClassNotFoundException {
     JSONParser parser = new JSONParser();
     String hostName = null;
     String dbName = null;
     String userName = null;
     String password= null;
     try {
         Object obj = parser.parse(new FileReader("src/database/ConfigDatabase.json"));
         JSONObject jsonObject = (JSONObject) obj;
         hostName = jsonObject.get("hostName").toString();
         dbName = jsonObject.get("dbName").toString();
         userName = jsonObject.get("userName").toString();
         password = jsonObject.get("password").toString();
     } catch (Exception e) {
         e.printStackTrace();
     }
     return getMySQLConnection(hostName, dbName, userName, password);
 }
 
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {
     Class.forName("com.mysql.jdbc.Driver");
     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false";
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}
