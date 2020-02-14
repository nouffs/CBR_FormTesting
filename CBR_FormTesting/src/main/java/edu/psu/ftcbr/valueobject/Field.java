/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.valueobject;

import java.util.ArrayList;


public class Field {
 private String name;
 private String mandatory;
 private String optional;
 private String letters;
 private String digits;
 private String chars;
 private ArrayList<TestCase> testCases = new ArrayList<TestCase>();

 
 
 public enum Result {
  PASS,
  FAIL,
  NA
}

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mandatory
     */
    public String getMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @return the optional
     */
    public String getOptional() {
        return optional;
    }

    /**
     * @param optional the optional to set
     */
    public void setOptional(String optional) {
        this.optional = optional;
    }

    /**
     * @return the letters
     */
    public String getLetters() {
        return letters;
    }

    /**
     * @param letters the letters to set
     */
    public void setLetters(String letters) {
        this.letters = letters;
    }

    /**
     * @return the digits
     */
    public String getDigits() {
        return digits;
    }

    /**
     * @param digits the digits to set
     */
    public void setDigits(String digits) {
        this.digits = digits;
    }

    /**
     * @return the chars
     */
    public String getChars() {
        return chars;
    }

    /**
     * @param chars the chars to set
     */
    public void setChars(String chars) {
        this.chars = chars;
    }

    /**
     * @return the testCases
     */
    public ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * @param testCases the testCases to set
     */
    public void setTestCases(ArrayList<TestCase> testCases) {
        this.testCases = testCases;
    }


 
 


 
}
