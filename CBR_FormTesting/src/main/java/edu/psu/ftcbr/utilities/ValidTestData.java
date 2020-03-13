/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.utilities;

/**
 *
 * @author abtsambdallh
 */
public class ValidTestData {
    
    public String getValidTestData(String fieldName){
        fieldName = fieldName.toLowerCase();
    switch(fieldName){
        case "username":
            return "TestName";
        case "name":
            return "TestName";
        case "email":
            return "test@test.test";
        case "password":
            return "password1234";
        case "id":
            return "1234567890";
        case "mobile number":
            return "0505050505";
        case "mobile":
            return "0505050505";
        default:
            return "failed";
       }
    
    }
    
}
