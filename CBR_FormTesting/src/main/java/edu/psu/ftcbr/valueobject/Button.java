/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.valueobject;

/**
 *
 * @author nfq
 */
public class Button {
  
    private String value;
    
    private String id;

    /**
     * @return the name
     */
    public String getValue() {
        return value;
    }

    /**
     * @param name the name to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
