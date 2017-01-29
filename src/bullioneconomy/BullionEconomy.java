/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullioneconomy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author yajnavalkya
 */
public class BullionEconomy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {
                
        System.out.println("Import to DB(1) or Predict(2)");
        Integer n = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
        switch(n){
            case 1: {
                BullionEconomy be = new BullionEconomy();
                be.csv2db();
                break;
            }
            case 2:{
                predictor pred = new predictor();
                pred.ARIMApredict();
                break;                
            }
            case 3:{                
                bullionchart chart = new bullionchart("Graph");
                chart.pack();
                RefineryUtilities.centerFrameOnScreen(chart);
                chart.setVisible(true);
                break;
            }
                
            default:System.out.println("Again");
        }
        
    }
    public void csv2db(){
        String[] path = {"./1.csv","./2.csv","./3.csv","./4.csv"};
        String l="";
        String p=",";
        csvparse cp = new csvparse();
        for (String path1 : path) {
            cp.csvparse(path1, l, p);
        }
        
    }
    
    }
    

