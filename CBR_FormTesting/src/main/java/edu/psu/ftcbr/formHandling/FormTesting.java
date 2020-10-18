/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.formHandling;

import edu.psu.ftcbr.valueobject.Button;
import edu.psu.ftcbr.valueobject.Field;
import edu.psu.ftcbr.valueobject.Form;
import edu.psu.ftcbr.valueobject.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FormTesting {

    public WebDriver driver;
    public String URL;
    public List<TestCase> mandatoryCases = new ArrayList<TestCase>();

    public List<TestCase> optionalCases = new ArrayList<TestCase>();

    public boolean success;

    public FormTesting() {
        success = false;
        TestCase testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept space input");
        testCase.setValue(" ");
        mandatoryCases.add(testCase);

        testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept empty value input");
        testCase.setValue("");
        mandatoryCases.add(testCase);

        testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept line break input");
        testCase.setValue(Keys.chord(Keys.SHIFT, Keys.ENTER));
        mandatoryCases.add(testCase);

        testCase = new TestCase();
        testCase.setDescription("Verify that the field accepts empty value input");
        testCase.setValue("");
        optionalCases.add(testCase);

    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void reset() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver(runInBG());
        //driver = new ChromeDriver();
        driver.get(URL);
        waitForLoad(driver);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public ChromeOptions runInBG() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--no-proxy-server");
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("window-size=1200x600");

        return options;
    }

    @Test
    public Form doTest(Form form) {
        // String status;
        System.out.println("*************** START ******************************** ");
        try {
            String caseValue;
            for (int i = 0; i < form.getFields().size(); i++) {//EACH FIELD
                if (form.getFields().get(i).isRequired()) {
                    //ASSIGN TEST CASES
                    form.getFields().get(i).getTestCases().addAll(getMandatory());
                } else {
                    form.getFields().get(i).getTestCases().addAll(getOpt());

                }
                for (int j = 0; j < form.getFields().get(i).getTestCases().size(); j++) { // EACH TEST CASE
                    //RESET DRIVER
                    reset();

                    //IF IT'S AN EMAIL, GET A UNIQUE EMAIL
                    if (form.getFields().get(i).getName().toLowerCase().contains("email")
                            && form.getFields().get(i).getTestCases().get(j).getDescription().toLowerCase().contains("a valid format")) {

                        caseValue = ValidTestData.getValidTestData(form.getFields().get(i).getName());
                    } else {
                        caseValue = form.getFields().get(i).getTestCases().get(j).getValue();
                    }
                    driver.findElement(By.id(form.getFields().get(i).getId())).sendKeys(caseValue);

                    for (int n = 0; n < form.getFields().size(); n++) {// OTHER FIELDS
                        if (!form.getFields().get(i).getId().equals(form.getFields().get(n).getId())) { // IF IT'S NOT THE SAME AS THE ONE WE ARE CURRENTLY TESTING
                            driver.findElement(By.id(form.getFields().get(n).getId())).sendKeys(ValidTestData.getValidTestData(form.getFields().get(n).getName()));

                        }

                    }// END OTHER FIELDS

                    driver.findElement(By.id(form.getButton().getId())).click();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    //  waitForLoad(driver);
                    try {
                        //  driver.findElement(By.xpath("//*[@id='" + form.getValidationElement() + "']/p[1]"));

                        String findSignUpForm = driver.findElement(By.id(form.getFormId())).getText();
                        // String errorMSG = driver.findElement(By.id("ctl00__bodyContent__valSum")).getText();
                        System.out.println(" > SIGNUP FORM IS STILL THERE >  ***************  ******************************** FIELD: " + caseValue + " ,   DESCR: " + form.getFields().get(i).getTestCases().get(j).getDescription());
                        //FAILED
                        form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? true : false);
                        //   System.out.println(form.getFields().get(i).getId()+" *************** PASSED??? "+form.getFields().get(i).getTestCases().get(j).isCasePassed()+" desc: "+form.getFields().get(i).getTestCases().get(j).getDescription());

                    } catch (Exception e) {
                        System.out.println(form.getFields().get(i).getId() + "*************** PAGE REDIRECTED ******************************** FIELD: " + form.getFields().get(i).getTestCases().get(j).getValue());
                        //PASSED
                        form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? false : true);

                        // System.out.println(form.getFields().get(i).getId()+" *************** PASSED??? "+form.getFields().get(i).getTestCases().get(j).isCasePassed()+" desc: "+form.getFields().get(i).getTestCases().get(j).getDescription());
                    }

                    success = true;
                    driver.close();
                    System.out.println(form.getFields().get(i).getId() + " *************** PASSED??? " + form.getFields().get(i).getTestCases().get(j).isCasePassed() + " desc: " + form.getFields().get(i).getTestCases().get(j).getDescription());

                }// END EACH TEST CASE
            }// EACH FIELD
            System.out.println("*************** DONE ******************************** ");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("form", form);
        } catch (WebDriverException ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        }

        return form;
    }

    @Test
    public Form doTestContactUs(Form form) {

        // String status;
        boolean waited = false;

        try {
            String caseValue;

            for (int i = 0; i < form.getFields().size(); i++) {//EACH FIELD
                if (form.getFields().get(i).isRequired()) {
                    //ASSIGN TEST CASES
                    form.getFields().get(i).getTestCases().addAll(getMandatory());
                } else {
                    form.getFields().get(i).getTestCases().addAll(getOpt());

                }

                for (int j = 0; j < form.getFields().get(i).getTestCases().size(); j++) { // EACH TEST CASE
                    //RESET DRIVER
                    reset();

                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // WAIT FOR THE MODAL TO SHOW

                    //IF IT'S AN EMAIL, GET A UNIQUE EMAIL
                    if (form.getFields().get(i).getName().toLowerCase().contains("email")
                            && form.getFields().get(i).getTestCases().get(j).getDescription().toLowerCase().contains("a valid format")) {

                        caseValue = ValidTestData.getValidTestData(form.getFields().get(i).getName());
                    } else {
                        caseValue = form.getFields().get(i).getTestCases().get(j).getValue();
                    }
                    closeDialogIfExists();
                    driver.findElement(By.id(form.getFields().get(i).getId())).sendKeys(caseValue);

                    for (int n = 0; n < form.getFields().size(); n++) {// OTHER FIELDS
                        if (!form.getFields().get(i).getId().equals(form.getFields().get(n).getId())) { // IF IT'S NOT THE SAME AS THE ONE WE ARE CURRENTLY TESTING
                            driver.findElement(By.id(form.getFields().get(n).getId())).sendKeys(ValidTestData.getValidTestData(form.getFields().get(n).getName()));
                            System.out.println("*************** FILLING ******************************** " + form.getFields().get(n).getName() + " WITH: " + ValidTestData.getValidTestData(form.getFields().get(n).getName()));

                        }

                    }// END OTHER FIELDS

                    By byXpath;
                    byXpath = By.xpath("//button[(@value='" + form.getButton().getValue() + "') and (@type = 'submit')] | //input[(@value='" + form.getButton().getValue() + "') and (@type = 'submit')] ");

                    if (driver.findElements(byXpath).size() > 0) {

                        driver.findElement(byXpath).click();
                    } else {
                        byXpath = By.xpath("//button[(@title='" + form.getButton().getValue() + "') and (@type = 'submit')] | //input[(@title='" + form.getButton().getValue() + "') and (@type = 'submit')]");

                        if (driver.findElements(byXpath).size() > 0) {
                            driver.findElement(byXpath).click();

                        } else if (driver.findElements(By.id(form.getButton().getId())).size() > 0) {

                            driver.findElement(By.id(form.getButton().getId())).click();
                        }
                    }
                    waitForLoad(driver);
                    try {
                        //  driver.findElement(By.xpath("//*[@id='" + form.getValidationElement() + "']/p[1]"));

                        String findContactUsForm = driver.findElement(By.id(form.getFormId())).getText();
                        // String errorMSG = driver.findElement(By.id("ctl00__bodyContent__valSum")).getText();

                        //PASS bcuz a success message is there
                        if (driver.findElements(By.className("success")).size() > 0) {
                            form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? false : true);

                        } else if (driver.findElements(By.className("form-success")).size() > 0) {
                            form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? false : true);

                        } else {
                            //FAILED
                            driver.findElement(By.id(form.getFields().get(i).getId())).sendKeys(caseValue);
                            form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? true : false);
                        }
                    } catch (Exception e) {
                        System.out.println(form.getFields().get(i).getId() + "*************** PAGE REDIRECTED ******************************** FIELD: " + form.getFields().get(i).getTestCases().get(j).getValue());
                        //PASSED
                        form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? false : true);
                    }

                    success = true;
                    driver.close();
                    System.out.println(form.getFields().get(i).getId() + " *************** PASSED??? " + form.getFields().get(i).getTestCases().get(j).isCasePassed() + "val:" + form.getFields().get(i).getTestCases().get(j).getValue() + "." + " desc: " + form.getFields().get(i).getTestCases().get(j).getDescription());

                }// END EACH TEST CASE
            }// EACH FIELD
            System.out.println("*************** DONE ******************************** ");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("form", form);
        } catch (WebDriverException ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        }
        driver.quit(); //close all browser windows once done
        return form;
    }

    public List<TestCase> getMandatory() {

        List<TestCase> mandatoryCases = new ArrayList<TestCase>();

        TestCase testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept space input");
        testCase.setValue(" ");
        mandatoryCases.add(testCase);

        testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept empty value input");
        testCase.setValue("");
        mandatoryCases.add(testCase);

        testCase = new TestCase();
        testCase.setDescription("Verify that the field does not accept line break input");
        testCase.setValue(Keys.chord(Keys.SHIFT, Keys.ENTER));
        mandatoryCases.add(testCase);

        return mandatoryCases;
    }

    public List<TestCase> getOpt() {

        List<TestCase> optionalCases = new ArrayList<TestCase>();
        TestCase testCase = new TestCase();

        testCase = new TestCase();
        testCase.setDescription("Verify that the field accepts empty value input");
        testCase.setValue("");
        optionalCases.add(testCase);
        return optionalCases;

    }

    public void closeDialogIfExists() {
        try {
            driver.findElement(By.className("action-close")).click();
        } catch (WebDriverException ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, "NO DIALOG FOUND " + ex);

        }
    }

}
