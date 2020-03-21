/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.formHandling;

import java.util.Random;

/**
 *
 * @author abtsambdallh
 */
public class ValidTestData {
   
    public static String getValidTestData(String fieldName){
        fieldName = fieldName.toLowerCase();
        // initialize a Random object somewhere; you should only need one
        Random random = new Random();
        // generate a random integer from 0 to 899, then add 100
        int x = random.nextInt(900) + 100;
        // create new email
        String email = x+"test"+x+"@test.test";
      String[] c= fieldName.split("[_ ]");
      for(int i = 0 ; i < c.length;i++){
          switch(c[i]){
        case "username":
            return "TestName";
        case "name":
            return "TestName";
        case "email":
            return email;
        case "password":
            return "password1234";
        case "id":
            return "1234567890";
        case "mobile number":
            return "0505050505";
        case "mobile":
            return "0505050505";
      
       }
      }
    return "failed";
    
    }
    
}
