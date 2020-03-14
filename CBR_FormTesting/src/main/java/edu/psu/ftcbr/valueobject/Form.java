/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.valueobject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nfq
 */
public class Form {

    private String formId;
    private List<Field> fields = new ArrayList<Field>();
    private Button button;
    private String validationElement; // ELEMENT WHERE THE VALDIATION MSG WILL BE DISPLAYED AT

    /**
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * @return the button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(Button button) {
        this.button = button;
    }

    /**
     * @return the validationElement
     */
    public String getValidationElement() {
        return validationElement;
    }

    /**
     * @param validationElement the validationElement to set
     */
    public void setValidationElement(String validationElement) {
        this.validationElement = validationElement;
    }

    /**
     * @return the formId
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

}
