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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class csvparse {
    public void csvparse(String path, String csvline, String csvsp) {

        String csvFile = path;
        String line = csvline;
        String cvsSplitBy = csvsp;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] value = line.split(cvsSplitBy); //Value Array                

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  
}
