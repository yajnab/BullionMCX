/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullioneconomy;

import java.sql.*;
/**
 *
 * @author yajnavalkya
 */
public class dbexporter {
    public void sqlentry(String[] value){
        try{  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/BULLION","yajnab","petrol123");
        
               
            // create a Statement from the connection
        Statement statement = con.createStatement();

        // insert the data
        statement.executeUpdate("INSERT INTO gold " + "VALUES("+value[0]+","+value[2]+");");
        }
     catch(ClassNotFoundException | SQLException e){
     System.out.println(e);}
    }
}

