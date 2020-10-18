/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.bean;

import edu.psu.ftcbr.formHandling.FormElements;
import edu.psu.ftcbr.formHandling.FormTesting;
import edu.psu.ftcbr.valueobject.Form;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nouf
 */
@ManagedBean
@ViewScoped
public class FormTestBean {

    private String url;
    private Form form;
    private String formType;
    private String notFoundMessage;
    private boolean showNotFound;

    public FormTestBean() {
//set default value for the seelcted type of form
        formType = "Contact";
    }

    public void fetchForm() {

        FormElements formElements = new FormElements();
        form = formType.equals("Contact")? formElements.getFormNew(url) :formElements.getForm(url) ;
        if (form == null) {
            showNotFound = true;
            notFoundMessage = "Please enter a valid form!";
        } else {
            showNotFound = false;
            notFoundMessage = "";
        }

    }

    public void testForm() {
        FormTesting test = new FormTesting();
        test.URL = url;
        test.doTestContactUs(form);

    }

    public String getUrl() {

        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Form getForm() {

        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getFormType() {

        return this.formType;
    }

    public String getNotFoundMessage() {
        return notFoundMessage;
    }

    public void setNotFoundMessage(String notFoundMessage) {
        this.notFoundMessage = notFoundMessage;
    }

    public boolean isShowNotFound() {
        return showNotFound;
    }

    public void setShowNotFound(boolean showNotFound) {
        this.showNotFound = showNotFound;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

}
