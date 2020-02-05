/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.tfcbr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NOUF
 */

public class DBManager {
 
 private static final String url = "jdbc:postgresql://localhost:5434/postgres";
 private static final String user = "postgres";
 private static final String password = "123";  


 // A methd that connects to the database and returns the Connection Object
public static Connection connect() {
          try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
                  e.printStackTrace();
        }
 
        return conn;
    }



 
}
