/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.bean;

import edu.psu.ftcbr.cbr.CBR;
import edu.psu.ftcbr.formHandling.FormElements;
import edu.psu.ftcbr.formHandling.FormTesting;
import edu.psu.ftcbr.utilities.HttpHandling;
import edu.psu.ftcbr.valueobject.Form;
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
    private Form form;
    private String url;
    private boolean noCases;
    FormTesting test;

    @PostConstruct
    public void onPageLoad() {

        showError = false;
        firstPage = true;
        secondPage = false;
        errorMessage = "";

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
            FormElements instance = new FormElements();
            form = instance.getForm(url);

            if (form == null) {
                errorMessage = "Sorry, couldn't find any form.";
                showError = true;
            } else if (form.getButton() == null) {
                errorMessage = "Sorry, couldn't find any form.";
                showError = true;
            } else {
                //CBR SECTION
                for (int i = 0; i < form.getFields().size(); i++) {

                    form.getFields().get(i).setTestCases(CBR.retrieve(form.getFields().get(i).getName()));
                    System.out.println("CASES FOR THIS FIELD" + form.getFields().get(i).getName() + "SIZE: " + form.getFields().get(i).getTestCases().size());
                }
 setNoCases(false);
                for (int i = 0; i < form.getFields().size(); i++) {
                    if (form.getFields().get(i).getTestCases().size() < 1) {
                        setNoCases(true);
                        break;
                    }
                }
                firstPage = false;
                secondPage = true;
                showError = false;
                errorMessage = "";
            }
        }
    }

    public void yes() {

        //TESTING SECTION
        test = new FormTesting();
        test.URL = url;
        form = test.doTest(form);
        if (test.success) { //MAKE SURE IT TESTED SUCCESSFULLY
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("form", form);
            errorMessage = "";
            showError = false;
        } else {

            showError = true;
            errorMessage = "Sorry, something went wrong while testing your e-form.";
        }

        firstPage = false;
        secondPage = false;
        thirdPage = true;
    }

    public void no() {
        errorMessage = "";
        showError = false;
        firstPage = true;
        secondPage = false;
        thirdPage = false;
        noCases = false;

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
     * @return the form
     */
    public Form getForm() {
        return form;
    }

    /**
     * @param form the form to set
     */
    public void setForm(Form form) {
        this.form = form;
    }

    public void resetBean() {
        form = null;
        url = "";
        errorMessage = "";
        firstPage = true;
        secondPage = false;
        thirdPage = false;
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("Fetch");
        test = null;

    }

    /**
     * @return the noCases
     */
    public boolean isNoCases() {
        return noCases;
    }

    /**
     * @param noCases the noCases to set
     */
    public void setNoCases(boolean noCases) {
        this.noCases = noCases;
    }

}
