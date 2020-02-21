/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.bean;

import edu.psu.ftcbr.cbr.CBR;
import edu.psu.ftcbr.utilities.CSVReadWrite;
import edu.psu.ftcbr.utilities.HttpHandling;
import edu.psu.ftcbr.valueobject.Field;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped

public class Cbrview {

     private Field field;

public Cbrview(){
field = new Field();
}



        //gets the entire csv file
    public void getCasesFromCBR(){
         
       
        field.setTestCases(CBR.retrieve(getField().getName()));
       
    }
    
    public void insertTestCaseInCSV(){
        TestCase testCase = new TestCase();
        testCase.setFieldName("PhoneNo");
        testCase.setCaseId("6");
        testCase.setDescription("just testing insert");
        testCase.setValue("3453@");
        //CBR.reuse(testCase);
    }
    
    public void insertTestCaseInCSV(String ID, String fieldName, String Desc, String testData){
        TestCase testCase = new TestCase();
        testCase.setFieldName(fieldName);
        testCase.setCaseId(ID);
        testCase.setDescription(Desc);
        testCase.setValue(testData);
        //CBR.retain(testCase);
        CSVReadWrite.insertCase(testCase);
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

// to test Retain function
 public void callRetainTest(){
    TestCase testcase1 = new TestCase();  
    TestCase testcase2 = new TestCase();  
    List<TestCase> testRetain = new ArrayList<TestCase>();
    testcase1.setCaseId("100");
    testcase1.setFieldName("testEbt");
    testcase1.setDescription("testEbt");
    testcase1.setValue("testEbt");
    testRetain.add(testcase1);
    
    testcase2.setCaseId("100");
    testcase2.setFieldName("testEbt2");
    testcase2.setDescription("testEbt2");
    testcase2.setValue("testEbt2");
    testRetain.add(testcase2);
    
    CBR cbr = new CBR();
    cbr.retain(testRetain, "NewField");
 }
    
    
   
    

}
