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

    public static String getValidTestData(String fieldName) {
        fieldName = fieldName.toLowerCase();
        // initialize a Random object somewhere; you should only need one
        Random random = new Random();
        // generate a random integer from 0 to 899, then add 100
        int x = random.nextInt(900) + 100;
        // create new email
        String email = x + "test" + x + "@test.test";
        String[] c = fieldName.split("[_ ]");
        for (int i = 0; i < c.length; i++) {

            if (c[i].toLowerCase().contains("username")) {
                return "TestName";
            } else if (c[i].toLowerCase().contains("name")) {
                return "TestName";
            } else if (c[i].toLowerCase().contains("first name")) {
                return "TestName";
            } else if (c[i].toLowerCase().contains("email")) {
                return email;
            } else if (c[i].toLowerCase().contains("password")) {
                return "password1234";
            } else if (c[i].toLowerCase().contains("id")) {
                return "1234567890";
            } else if (c[i].toLowerCase().contains("mobile number")) {
                return "0505050505";
            } else if (c[i].toLowerCase().contains("mobile")) {
                return "0505050505";
            } else if (c[i].toLowerCase().contains("number")) {
                return "0505050505";
            } else if (c[i].toLowerCase().contains("telephone")) {
                return "0505050505";
            } else if (c[i].toLowerCase().contains("phone")) {
                return "0505050505";
            } else if (c[i].toLowerCase().contains("comment")) {
                return "test comment";
            } else if (c[i].toLowerCase().contains("message")) {
                return "test message";
            } else if (c[i].toLowerCase().contains("body")) {
                return "test message";
            }

        }
        return "failed";

    }

}
