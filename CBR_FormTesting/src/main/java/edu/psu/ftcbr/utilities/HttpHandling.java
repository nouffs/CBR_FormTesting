/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.utilities;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HttpHandling {

    
    
    public static boolean pageExists(String url){
    boolean exists= false;
try {
    Document document = Jsoup.connect(url).validateTLSCertificates(false).get();
    exists= true;
} catch (UnknownHostException e) {
    exists= false;
    System.err.println("Unknown host");
    e.printStackTrace(); // I'd rather (re)throw it though.
}       catch (IOException ex) {
            Logger.getLogger(HttpHandling.class.getName()).log(Level.SEVERE, null, ex);
            exists= false;
        }


   return exists; }
    
}
