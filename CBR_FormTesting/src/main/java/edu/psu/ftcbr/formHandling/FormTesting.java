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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    public void reset() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        driver = new ChromeDriver(runInBG());
        driver.get(URL);

        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

    }

    public ChromeOptions runInBG() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
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
                    form.getFields().get(i).getTestCases().addAll(mandatoryCases);
                }
                else {
                                    form.getFields().get(i).getTestCases().addAll(optionalCases);

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
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    try {
                        //  driver.findElement(By.xpath("//*[@id='" + form.getValidationElement() + "']/p[1]"));

                        String findSignUpForm = driver.findElement(By.id(form.getFormId())).getText();
                        // String errorMSG = driver.findElement(By.id("ctl00__bodyContent__valSum")).getText();
                        System.out.println(" > SIGNUP FORM IS STILL THERE >  ***************  ******************************** FIELD: " + caseValue + " ,   DESCR: " + form.getFields().get(i).getTestCases().get(j).getDescription());
                        //FAILED
                        form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? true : false);
                    } catch (Exception e) {
                        System.out.println("*************** PAGE REDIRECTED ******************************** FIELD: " + form.getFields().get(i).getTestCases().get(j).getValue());
                        //PASSED
                        form.getFields().get(i).getTestCases().get(j).setCasePassed(form.getFields().get(i).getTestCases().get(j).getDescription().contains("not") ? false : true);
                    }

                    success = true;
                    driver.close();

                }// END EACH TEST CASE
            }// EACH FIELD
            System.out.println("*************** DONE ******************************** ");
        } catch (WebDriverException ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(FormTesting.class.getName()).log(Level.SEVERE, null, ex);

        }

        return form;
    }

}
