/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import User.User;
import database.UsersTable;
import netscape.security.UserTarget;

import java.sql.SQLException;

/**
 *
 * @author daonv
 */
public class TestMySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            UsersTable.connection();
            UsersTable.upDateById(4,"test","1234");
            UsersTable.printAllUsers();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
