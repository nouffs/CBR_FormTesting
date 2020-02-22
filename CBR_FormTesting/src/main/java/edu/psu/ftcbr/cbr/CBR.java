/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.cbr;

import edu.psu.ftcbr.bean.Cbrview;
import edu.psu.ftcbr.utilities.CSVReadWrite;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.List;

public class CBR {

    
    //Will return similar cases
    public static List<TestCase> retrieve(String fieldName) {

        List<TestCase> allCases = new ArrayList<TestCase>();

        allCases = CSVReadWrite.readTestCasesFromCSV();

        List<TestCase> similarCases = new ArrayList<TestCase>();
        int lastInserted=-1;
        for (int i = 0; i < allCases.size(); i++) {
            if (allCases.get(i).getFieldName().trim().equals(fieldName)) {
                similarCases.add(allCases.get(i));
                lastInserted++;
                similarCases.get(lastInserted).setSimilarity(1);
            }

        }
        
        if (similarCases.size() == 0){
           similarCases.addAll(revise(fieldName));
           
          if (similarCases.size() > 0)
              retain(similarCases, fieldName);
        }
        return similarCases;
    }

    public static void reuse(TestCase onecase) {
        
       // Ebtesam: I moved this line into retain 
       //CSVReadWrite.insertCase(onecase);
    }
    
    
   //Will return less similar cases
    public static List<TestCase> revise(String fieldName) {
        List<TestCase> similarCases = new ArrayList<TestCase>();
        
        
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

}
