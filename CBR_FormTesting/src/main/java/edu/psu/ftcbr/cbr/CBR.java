/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.cbr;

import edu.psu.ftcbr.bean.Cbrview;
import edu.psu.ftcbr.utilities.CSVReadWrite;
import edu.psu.ftcbr.valueobject.StringSimilarity;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static javax.swing.UIManager.get;

public class CBR {

    // ********* EBTESAM: Just to test the enhanced retreival process ********//
    /* 
    public static void main(String[] args) {
         retrieve("num");
     
     }
    */
    //Will return similar cases
    public static List<TestCase> retrieve(String fieldName) {

        List<TestCase> allCases = new ArrayList<TestCase>();

        allCases = CSVReadWrite.readTestCasesFromCSV();

        List<TestCase> similarCases = new ArrayList<TestCase>();
        int lastInserted=-1;
        for (int i = 0; i < allCases.size(); i++) {
            if (allCases.get(i).getFieldName().trim().toLowerCase().equals(fieldName.toLowerCase())) {
                similarCases.add(allCases.get(i));
                lastInserted++;
                similarCases.get(lastInserted).setSimilarity(1);
            }
            
        
        }
        
        if (similarCases.size() == 0){
           similarCases.addAll(revise(fieldName,allCases));
           
          if (similarCases.size() > 0){
              mostSimilarCases(similarCases, fieldName);
             // retain(similarCases, fieldName);
          }
        }
        return similarCases;
    }

    public static void reuse(TestCase onecase) {
        
       // Ebtesam: I moved this line into retain 
       //CSVReadWrite.insertCase(onecase);
    }
    
    
   //Will return less similar cases
    public static List<TestCase> revise(String fieldName,List<TestCase> allCases)  {
        String s1;
        String s2;
        List<TestCase> similarCases = new ArrayList<TestCase>();
        int lastInserted=-1;
        StringSimilarity ssm = new StringSimilarity();
        
        for (int i = 0; i < allCases.size(); i++) {
           //  if (allCases.get(i).getFieldName().trim().toLowerCase().contains(fieldName.toLowerCase())) {
           s1 = allCases.get(i).getFieldName().trim().toLowerCase();
           s2 = fieldName.toLowerCase();
           if (ssm.calcSimilarity(s1,s2)!= 0){
                //s1 = allCases.get(i).getFieldName().trim().toLowerCase();
              //  s2 = fieldName.toLowerCase();
                similarCases.add(allCases.get(i));
                lastInserted++;
                //Double d = similarCases.get(lastInserted).calcSimilarity(s1,s2);
                similarCases.get(lastInserted).setSimilarity(ssm.calcSimilarity(s1,s2));
            }
           /*
             else if (fieldName.toLowerCase().contains(allCases.get(i).getFieldName().trim().toLowerCase())){
                s1 = fieldName.toLowerCase();
                s2 = allCases.get(i).getFieldName().trim().toLowerCase();
                similarCases.add(allCases.get(i));
                lastInserted++;
                Double d = similarCases.get(lastInserted).calcSimilarity(s1,s2);
                similarCases.get(lastInserted).setSimilarity(d);
             }
           */
        } 
        
        
        
        // EBTESAM: I commented the previous less similar retrieval process
        /*
        String[] c= fieldName.split("[_ ]");
        int lastInserted=-1;
        for(int j=0;j<c.length;j++){
        for (int i = 0; i < allCases.size(); i++) {
            if (allCases.get(i).getFieldName().trim().toLowerCase().equals(c[j].toLowerCase())) {
                similarCases.add(allCases.get(i));
                lastInserted++;
                similarCases.get(lastInserted).setSimilarity(0.5);
            }
        
          
        }
        
     
        
        
        

        }    
          //If no matches were found, then try to check if the entered fieldName contains a value from the repository
        if (similarCases.size() == 0) {
            String[] b;
            for (int i = 0; i < allCases.size(); i++) {
                b = allCases.get(i).getFieldName().split("[_ ]");

                for (int j = 0; j < b.length; j++) {
                    if (fieldName.toLowerCase().equals(b[j].toLowerCase())) {
                        similarCases.add(allCases.get(i));
                        lastInserted++;
                        similarCases.get(lastInserted).setSimilarity(0.5);
                    }
                }

            }
        }
*/
        
        return similarCases;
    }
//store new test case
    public static void retain(List<TestCase> newFieldCases, String fieldName) {
        String ID, name, desc, testData;
        Cbrview cbrView = new Cbrview();
        CSVReadWrite read = new CSVReadWrite();
        int csvSize = read.readTestCasesFromCSV().size();
        int size = newFieldCases.size();
        for (int i = 0; i < size; i++)
        {
            ID = csvSize + i + 1 + "";
            name= fieldName;
            desc = newFieldCases.get(i).getDescription();
            testData = newFieldCases.get(i).getValue();
            cbrView.insertTestCaseInCSV(ID,name,desc,testData);
        
        }
        //CSVReadWrite.insertCase(onecase);
    
    }
    
    public static void mostSimilarCases(List<TestCase> newSimilarCases, String fieldName){
        List<TestCase> mostSimilarCases = new ArrayList<TestCase>();
        TestCase tc = Collections.max(newSimilarCases, Comparator.comparingDouble(TestCase::getSimilarity));
        System.out.println("*************************"+tc.getSimilarity()+ "******"+tc.getFieldName());
        Double maxSimilarity = tc.getSimilarity();
        String mostSimilarField = tc.getFieldName();
       
        for (int i = 0; i < newSimilarCases.size(); i++) {
           if (newSimilarCases.get(i).getSimilarity() == maxSimilarity)
               if (newSimilarCases.get(i).getFieldName().equals(mostSimilarField))
                   mostSimilarCases.add(newSimilarCases.get(i));     
        }
        
        if (mostSimilarCases.size() > 0)
            retain (mostSimilarCases, fieldName);
    }
    

}
