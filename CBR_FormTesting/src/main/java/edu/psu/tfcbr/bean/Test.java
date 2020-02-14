/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.tfcbr.bean;

import edu.psu.ftcbr.utilities.TestFormSubmit;
import edu.psu.tfcbr.db.TestDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author NOUF
 */

//This is a test bean

@ManagedBean
@ViewScoped
public class Test {
    
    private int id;
    private String name;
    private String address; 
    
public void createTable(){

    //calling the DAO to create the table
 //TestDAO.createTable();
    TestFormSubmit test = new TestFormSubmit();
 test.testGitHub();
}   



public void insertData(){
 
    TestDAO.insertToTable(id, name, address);

}

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }




}
