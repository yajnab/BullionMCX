/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullioneconomy;

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
public class predictor {
    public void ARIMApredict() throws SQLException, ClassNotFoundException{
        ArrayList<Double> arraylist; // TODO Auto-generated catch block
        arraylist = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BULLION","yajnab","petrol123");
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT price FROM gold");
        while(result.next())
        {
            
            //System.out.println(result.getDouble(1));
            arraylist.add(result.getDouble(1));
        }
        
        
        //System.out.println(arraylist.size());
        
        //int []model=arima.getARIMAmodel();
        //System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
        //System.out.println("Predict value="+arima.aftDeal(arima.predictValue(model[0],model[1])));
        //System.out.println("Predict error="+((arima.aftDeal(arima.predictValue(model[0],model[1]))-arraylist.get(arraylist.size()-1))/arraylist.get(arraylist.size()-1))*100+"%");
        //String[] str = (String[])list1.toArray(new String[0]);
        result.close();
        ArrayList<Double> ar = new ArrayList<>();
        for(int i=0;i<450;i++){
            ar.add(arraylist.get(i));//Basic Feeder
        }
        for(int i=450;i<arraylist.size()-1;i++){//Prediction starts from the 46th value and ends at the last
            double[] dataArray=new double[i];
            for(int k=0;k<ar.size()-1;k++)                
                dataArray[k]=ar.get(k);
            ARIMA arima=new ARIMA(dataArray);
            int []model=arima.getARIMAmodel();
            int p = model[0], q= model[1]; 
            //System.out.println(p+"\t"+q); //Debug Code for Model Printing
            double pv=arima.aftDeal(arima.predictValue(p,q));
            System.out.println("Predict value="+pv);
            ar.add(pv);         
            
        }       
        }
	
}
