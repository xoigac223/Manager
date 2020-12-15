package service;

import service.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    protected Connection conn;

    public Database(){
        try {
            this.conn = ConnectionUtils.getMyConnection();
        } catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        if (this.conn == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return this.conn != null;
    }
}
