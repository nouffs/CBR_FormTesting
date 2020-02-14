/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.tfcbr.bean;

import edu.psu.ftcbr.utilities.HttpHandling;
import edu.psu.ftcbr.valueobject.Field;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped

public class Fetch {

    private String errorMessage;
    private boolean showError;
    private boolean firstPage;
    private boolean secondPage;
    private boolean thirdPage;
    private ArrayList<Field> fields = new ArrayList<Field>();
    private boolean showLoading;

    private String url;

    @PostConstruct
    public void onPageLoad() {

        showError = false;
        firstPage = true;
        secondPage = false;

        //initializing fields [[for testing]]
        Field field;
        ArrayList<TestCase> testcases;
        TestCase onecase;

        field = new Field();
        field.setName("First Name");
        field.setMandatory(Field.Result.PASS.toString());
        field.setOptional(Field.Result.NA.toString());
        field.setLetters(Field.Result.PASS.toString());
        field.setDigits(Field.Result.FAIL.toString());
        field.setChars(Field.Result.PASS.toString());

        testcases = new ArrayList<TestCase>();
        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept empty values");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept space only Input ");
        onecase.setCasePassed(false);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept line break input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept special characters input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept digits input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("verify that the field accepts letters input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        field.setTestCases(testcases);
        /* 
            
            
             Verify that the field does not accept empty values                                       Pass
     Verify that the field does not accept space only Input                                   Fail
     Verify that the field does not accept line break input                                   Pass
     Verify that the field does not accept special characters input                           Pass
     Verify that the field does not accept digits input                                       Pass
     verify that the field accepts letters input
         */

        fields.add(field);
        field = new Field();
        field.setName("Last Name");
        field.setMandatory(Field.Result.NA.toString());
        field.setOptional(Field.Result.PASS.toString());
        field.setLetters(Field.Result.PASS.toString());
        field.setDigits(Field.Result.FAIL.toString());
        field.setChars(Field.Result.PASS.toString());

        testcases = new ArrayList<TestCase>();
        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept empty values");
        onecase.setCasePassed(false);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept space only Input ");
        onecase.setCasePassed(false);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept line break input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept special characters input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept digits input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("verify that the field accepts letters input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        field.setTestCases(testcases);

        /*
              Verify that the field accepts empty values                                               Fail
     Verify that the field does not accept space only input                                   Fail
     Verify that the field does not accept line break input                                   Pass
     Verify that the field does not accept special characters input                           Pass
     Verify that the field does not accept digits input                                       Pass
     Verify that the field accepts fetters input                                              Pass
             
         */
        fields.add(field);
        field = new Field();
        field.setName("Email");
        field.setMandatory(Field.Result.PASS.toString());
        field.setOptional(Field.Result.NA.toString());
        field.setLetters(Field.Result.PASS.toString());
        field.setDigits(Field.Result.PASS.toString());
        field.setChars(Field.Result.PASS.toString());

        testcases = new ArrayList<TestCase>();
        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept empty values");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept space only input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept line break input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field accepts emails written in a valid format (xxx@xxx.com)");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        field.setTestCases(testcases);

        /*
         
     Verify that the field does not accept empty values                                       Pass
     Verify that the field does not accept space only input                                   Pass
     Verify that the field does not accept line break input                                   Pass
     Verify that the field accepts emails written in a valid format
                                                                                              Pass
     "xxx@xxx"                                           Pass
             
         */
        fields.add(field);
        field = new Field();
        field.setName("Password");
        field.setMandatory(Field.Result.PASS.toString());
        field.setOptional(Field.Result.NA.toString());
        field.setLetters(Field.Result.PASS.toString());
        field.setDigits(Field.Result.PASS.toString());
        field.setChars(Field.Result.FAIL.toString());

        testcases = new ArrayList<TestCase>();
        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept empty values");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field does not accept space only input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText(" Verify that the field does not accept line break input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field accepts special characters input");
        onecase.setCasePassed(false);
        testcases.add(onecase);

        onecase = new TestCase();
        onecase.setCaseText("Verify that the field accepts digits input");
        onecase.setCasePassed(true);
        testcases.add(onecase);
        onecase = new TestCase();
        onecase.setCaseText("Verify that the field accepts letters input");
        onecase.setCasePassed(true);
        testcases.add(onecase);

        field.setTestCases(testcases);

        fields.add(field);
        /*
         
     Verify that the field does not accept empty values                                       Pass
     Verify that the field does not accept space only input                                   Pass
     Verify that the field does not accept line break input                                   Pass
     Verify that the field accepts special characters input                                   Fall
     Verify that the field accepts digits input                                               Pass
     Verify that the field accepts letters input                                            Pass
             
         */

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("fields", fields);

    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the firstPage
     */
    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * @param firstPage the firstPage to set
     */
    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    /**
     * @return the secondPage
     */
    public boolean isSecondPage() {
        return secondPage;
    }

    /**
     * @param secondPage the secondPage to set
     */
    public void setSecondPage(boolean secondPage) {
        this.secondPage = secondPage;
    }

    /**
     * @return the showError
     */
    public boolean isShowError() {
        return showError;
    }

    /**
     * @param showError the showError to set
     */
    public void setShowError(boolean showError) {
        this.showError = showError;
    }

    public void startTesting() {

        if (url.isEmpty() || url == null) {

            errorMessage = "Please insert a URL to be tested.";
            showError = true;
        } else if (!url.startsWith("http://") && !url.startsWith("https://")) {
            errorMessage = "Please insert a valid URL.";
            showError = true;

        } else if (url.endsWith("//")) {
            errorMessage = "Please insert a valid URL.";
            showError = true;

        } else if (!url.contains("//")) {
            errorMessage = "Please insert a valid URL.";
            showError = true;

        } else if (!HttpHandling.pageExists(url)) {
            System.out.println("The inserted page does not exist!" + url + HttpHandling.pageExists(url));
            errorMessage = "The inserted page does not exist!";
            showError = true;

        } else {

            firstPage = false;
            secondPage = true;
        }
    }

    public void load() {
        System.out.println("loooooaaaddding");
        showLoading = true;
        firstPage = false;
        secondPage = false;
        thirdPage = false;

    }

    public void yes() {
        System.out.println("sleepppppppppppp");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fetch.class.getName()).log(Level.SEVERE, null, ex);
        }
        showLoading = false;
        firstPage = false;
        secondPage = false;
        thirdPage = true;
    }

    public void no() {
        firstPage = true;
        secondPage = false;
        thirdPage = false;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the thirdPage
     */
    public boolean isThirdPage() {
        return thirdPage;
    }

    /**
     * @param thirdPage the thirdPage to set
     */
    public void setThirdPage(boolean thirdPage) {
        this.thirdPage = thirdPage;
    }

    /**
     * @return the fields
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    /**
     * @return the showLoading
     */
    public boolean isShowLoading() {
        return showLoading;
    }

    /**
     * @param showLoading the showLoading to set
     */
    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

}
