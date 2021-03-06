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

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Double> ARIMApredict() throws SQLException, ClassNotFoundException{
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
        con.close();
        ArrayList<Double> ARFinal = new ArrayList<>();
        ArrayList<Double> ar = new ArrayList<>();
        for(int i=0;i<40;i++){
            ar.add(arraylist.get(i));//Basic Feeder
            ARFinal.add(arraylist.get(i));
        }
        for(int i=40;i<arraylist.size();i++){//Prediction starts here
            double[] dataArray=new double[i];
            if(i>280){
                for(int k=0;k<ARFinal.size()-1;k++)
                    dataArray[k]=ARFinal.get(k);}
            else{
                for(int k=0;k<ARFinal.size()-1;k++)
                    dataArray[k]=arraylist.get(k);}
            ARIMA arima=new ARIMA(dataArray);
            int []model=arima.getARIMAmodel();
            int p = model[0], q= model[1]; 
            //System.out.println(p+"\t"+q); //Debug Code for Model Printing
            double pv=arima.aftDeal(arima.predictValue(p,q));
            //System.out.println("Predict value="+pv);
            ARFinal.add(pv);
            
        }
        return ARFinal;
        }
	
}
