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
public class BullionEconomy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] path = {"./1.csv","2.csv"};
        String l="";
        String p=",";
        csvparse cp = new csvparse();
        for (String path1 : path) {
            cp.csvparse(path1, l, p);
        }
        
    }
    
}
