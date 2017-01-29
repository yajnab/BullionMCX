/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullioneconomy;

/**
 *
 * @author yajnavalkya
 */

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 */
public class bullionchart extends ApplicationFrame {

    
    /**
     * A demonstration application showing how to create a simple time series 
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     */
    public bullionchart(String title) throws ClassNotFoundException, SQLException, ParseException {
        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Gold Price in Recent Years",  // title
            "Date",             // x-axis label
            "Price Per 10g",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            //renderer.setDefaultShapesVisible(true);
            //renderer.setDefaultShapesFilled(true);
        }
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        
        return chart;

    }
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    private static XYDataset createDataset() throws ClassNotFoundException, SQLException, ParseException {

        
        
        TimeSeries s1 = new TimeSeries("Actual", Day.class);
        TimeSeries s2 = new TimeSeries("Forecasted", Day.class);
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BULLION","yajnab","petrol123")) {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM gold");
            ArrayList<Double> arm = new ArrayList<>();
            predictor pcd = new predictor();
            arm= pcd.ARIMApredict();int i=0;
            while(result.next())
            {
                
                String datefeed = result.getString(1);
                Double value = result.getDouble(2);
                int[] m=  new  int[3];
                //bullionchart bcc = new bullionchart();
                //m = bcc.dateget(datefeed);
                m = dateget(datefeed);
                s1.add(new Day(m[0],m[1],m[2]), value);
                s2.add(new Day(m[0],m[1],m[2]), arm.get(i));
                i++;
            }   result.close();
            /*s1.add(new Month(2, 2001), 181.8);
            s1.add(new Month(3, 2001), 167.3);
            s1.add(new Month(4, 2001), 153.8);
            s1.add(new Month(5, 2001), 167.6);
            s1.add(new Month(6, 2001), 158.8);
            s1.add(new Month(7, 2001), 148.3);
            s1.add(new Month(8, 2001), 153.9);
            s1.add(new Month(9, 2001), 142.7);
            s1.add(new Month(10, 2001), 123.2);
            s1.add(new Month(11, 2001), 131.8);
            s1.add(new Month(12, 2001), 139.6);
            s1.add(new Month(1, 2002), 142.9);
            s1.add(new Month(2, 2002), 138.7);
            s1.add(new Month(3, 2002), 137.3);
            s1.add(new Month(4, 2002), 143.9);
            s1.add(new Month(5, 2002), 139.8);
            s1.add(new Month(6, 2002), 137.0);
            s1.add(new Month(7, 2002), 132.8);*/
        }

        /*TimeSeries s2 = new TimeSeries("Forecasted", Month.class);
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);*/

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        dataset.setDomainIsPointsInTime(true);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() throws ClassNotFoundException, SQLException, ParseException {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    
    public static int[] dateget(String i) throws ParseException{
        String inputDateString = i.trim().replaceAll(" ", "-");
        System.out.println(inputDateString);
        DateFormat dfTo = new SimpleDateFormat("dd-MMM-yyyy");
        Date inputDate = dfTo.parse(inputDateString);
        DateFormat dayf = new SimpleDateFormat("dd");
        DateFormat monthf = new SimpleDateFormat("MM");
        DateFormat yearf = new SimpleDateFormat("yyyy");
        String oDay = dayf.format(inputDate);
        String oMonth = monthf.format(inputDate);
        String oYear = yearf.format(inputDate);
        int[] k = new int[3];
        k[0]=Integer.parseInt(oDay.toString());
        k[1]=Integer.parseInt(oMonth.toString());
        k[2]=Integer.parseInt(oYear.toString());
        
        return k;
    }
}

