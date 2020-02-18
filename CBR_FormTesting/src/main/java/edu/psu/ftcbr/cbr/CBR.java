/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.cbr;

import edu.psu.ftcbr.utilities.CSVReadWrite;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.List;

public class CBR {

    
    //Will return similar cases
    public static List<TestCase> retrieve(String name) {
        
        List<TestCase> similarCases = new ArrayList<TestCase>();
        
        similarCases = CSVReadWrite.readTestCasesFromCSV();
        
        
        return similarCases;
    }

    public static void reuse(TestCase onecase) {
        
        CSVReadWrite.insertCase(onecase);
    }
    
    
   //Will return less similar cases
    public List<TestCase> revise() {
        List<TestCase> similarCases = new ArrayList<TestCase>();
        
        
        return similarCases;
    }
//store new test case
    public void retain() {
    }

}
