/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.valueobject;


public class TestCase {
    private String caseId;
    private String fieldName;
    private boolean casePassed;
private String description;
private String value;
private double similarity;

    /**
     * @return the casePassed
     */
    public boolean isCasePassed() {
        return casePassed;
    }

    /**
     * @param casePassed the casePassed to set
     */
    public void setCasePassed(boolean casePassed) {
        this.casePassed = casePassed;
    }

    /**
     * @return the caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the similarity
     */
    public double getSimilarity() {
        return similarity;
    }

    /**
     * @param similarity the similarity to set
     */
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    
    
}
