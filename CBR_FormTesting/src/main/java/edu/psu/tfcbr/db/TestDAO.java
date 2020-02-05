/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.psu.tfcbr.db;

import edu.psu.tfcbr.db.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NOUF
 */
public class TestDAO {
    
    
    
    public static void createTable(){

 Connection conn = null;
      PreparedStatement  stmt= null;
      try {
    conn=DBManager.connect(); //getting connection from DBManager
    
    
            String CreateSql = "Create Table TEST_TABLE(id int primary key,  name varchar, address text) ";

                stmt =  conn.prepareStatement(CreateSql); 


        stmt.executeUpdate();
            System.out.println("inserted.");

           
 } catch (SQLException ex) {
           Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
    
      
      //Connection and statements have to be closed after every execution
    finally{
         try {
                if(conn != null)
                conn.close();   
            if (stmt !=null)
                stmt.close();
     } catch (SQLException ex) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
}   
    
    
    

public static void insertToTable(int id, String name, String address){
 Connection conn = null;
      PreparedStatement  stmt= null;
     
      try {
    conn=DBManager.connect(); //getting connection from DBManager
    
    
            String CreateSql = "INSERT INTO TEST_TABLE(id,  name, address) VALUES (?, ?, ?)";

                stmt =  conn.prepareStatement(CreateSql); 
stmt.setInt(1,id);
stmt.setString(2, name);
stmt.setString(3, address);
        stmt.executeUpdate();
            System.out.println("inserted.");

           
 } catch (SQLException ex) {
           Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
    
      
      //Connection and statements have to be closed after every execution
    finally{
         try {
                if(conn != null)
                conn.close();   
            if (stmt !=null)
                stmt.close();
     } catch (SQLException ex) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
      }

}

    
    
}
