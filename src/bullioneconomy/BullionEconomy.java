/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullioneconomy;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yajnavalkya
 */
public class BullionEconomy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                
        predictor pred = new predictor();
        pred.ARIMApredict();
    }
    public void csv2db(){
        String[] path = {"./1.csv","2.csv"};
        String l="";
        String p=",";
        csvparse cp = new csvparse();
        for (String path1 : path) {
            cp.csvparse(path1, l, p);
        }
        
    }
    
    }
    

