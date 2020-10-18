/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.formHandling;

import edu.psu.ftcbr.cbr.CBR;
import edu.psu.ftcbr.valueobject.Button;
import edu.psu.ftcbr.valueobject.Field;
import edu.psu.ftcbr.valueobject.Form;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author nfq
 */
public class FormElements {
    // # Constants

    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    // # GET THE FORM INCL. ITS FIELDS + SUBMIT BUTTON
    public Form getForm(String url) {

        Form formFound = null;
        List<Field> fields = new ArrayList<Field>();
        Field field;
        Button formButton = null;
        try {

            Connection.Response formResponse = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT).validateTLSCertificates(false)
                    .execute();

            // ## Find the form first...
            FormElement formObject;
            formObject = (FormElement) formResponse.parse()
                    .select("form[method='post']").first();

            checkElement("Form", formObject);
            Document docForm = Jsoup.parse(formObject.html()); //parse the html of the form

            Elements inputs = docForm.select("input").not("[type='hidden']").not("[type='submit']"); //then access the one at 0 index

            Element button;
            Elements buttons = docForm.select("input[type='submit']"); //then access the one at 0 index
            if (buttons.size() > 0) {
                button = buttons.get(0); //then access the one at 0 index
                if (button.attr("value") != null && button.attr("id") != null) {
                    formButton = new Button();
                    formButton.setId(button.attr("id"));
                    formButton.setValue(button.attr("value"));
                }

            }
            //FILLING INPUT FIELDS OBJECTS
            for (int i = 0; i < inputs.size(); i++) {

                field = new Field();
                field.setName(inputs.get(i).attr("name"));
                field.setId(inputs.get(i).attr("id"));

                Elements spans = docForm.select("span[id$='" + field.getId() + "']");//CHECK IF REQUIERED
                if (spans != null) {
                    if (spans.size() > 0) {
                        if (spans.get(0).text().equals("*")) {
                            System.out.println("************* IT REQQQ FIELD" + field.getId());
                            field.setRequired(true);
                        } else {
                            field.setRequired(false);
                        }
                    } else {
                        field.setRequired(false);
                    }
                } else {
                    field.setRequired(false);
                }
                fields.add(field);
            }
            formFound = new Form();
            formFound.setButton(formButton);
            formFound.setFields(fields);
            formFound.setFormId(formObject.attr("id"));

        } catch (IOException ex) {
            Logger.getLogger(FormElements.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(FormElements.class.getName()).log(Level.SEVERE, null, ex);

        }
        // formFound.setValidationElement("ctl00__bodyContent__valSum");// VALIDATION ELEMENT WHERE AN ERROR MESSAGE WILL BE DISPLAYED
        return formFound;
    }

    //GET FORM (((( NEW ))))
    // # GET THE FORM INCL. ITS FIELDS + SUBMIT BUTTON
    public Form getFormNew(String url) {

        Form formFound = null;
        List<Field> fields = new ArrayList<Field>();
        Field field;
        Button formButton = null;
        try {

            Connection.Response formResponse = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT).validateTLSCertificates(false)
                    .execute();

            Document htmlContent = formResponse.parse();

            // ## Find the form first...
            FormElement formObject;
            formObject = (FormElement) htmlContent
                    .select("form[method='post']").first();

            //if no form with method = post is found, then jsut look for a form 
            if (formObject == null) {

                /* formObject = (FormElement) htmlContent
                    .select("form").first();*/
            }

            checkElement("Form", formObject);
            Document docForm = Jsoup.parse(formObject.html()); //parse the html of the form

            Elements inputs = docForm.select("input").not("[type='hidden']").not("[type='submit']"); //then access the one at 0 index
            inputs.addAll(docForm.select("textarea").not("[type='hidden']").not("[type='submit']")); //tgetTextarea

            Element button;
            Elements buttons = docForm.select("input[type='submit'], button[type='submit']"); //then access the one at 0 index

            if (buttons.size() > 0) {
                button = buttons.get(0); //then access the one at 0 index

                if (button.hasAttr("value") && button.hasAttr("id")) {
                    formButton = new Button();
                    formButton.setId(button.attr("id"));
                    formButton.setValue(button.attr("value"));
                } else if (button.hasAttr("title")) {
                    formButton = new Button();
                    // formButton.setId(button.attr("id")); IT MIGHT HAVE NO ID
                    formButton.setValue(button.attr("title"));
                }
                else if (button.hasAttr("value")) {
                    formButton = new Button();
                    // formButton.setId(button.attr("id")); IT MIGHT HAVE NO ID
                    formButton.setValue(button.attr("value"));
                }

            }
            //FILLING INPUT FIELDS OBJECTS
            for (int i = 0; i < inputs.size(); i++) {

                /*   for(Attribute att : inputs.get(i).attributes().asList()){ // for each tag get all attributes in one List<Attribute>
        System.out.println("Key: "+att.getKey()+ " , Value: "+att.getValue());
        System.out.println();
    } */
                //System.out.println("------------------  " + inputs.get(i).attr("name") + "  .  " + inputs.get(i).attr("", ""));
                field = new Field();
                field.setName(inputs.get(i).attr("name"));
                field.setId(inputs.get(i).attr("id"));
                field.setType(inputs.get(i).tagName());
               
//CHECK IF IT'S A REQUIRED FIELD
                Elements spans = docForm.select("span[id$='" + field.getId() + "']");//CHECK IF REQUIERED
                if (spans == null) //TO AVOID NULL EXCEPTION
                {
                    spans = new Elements();
                }

                if (spans.size() > 0 && spans.get(0).text().equals("*")) {

                    field.setRequired(true);

                } else if (inputs.get(i).hasAttr("required")) {

                    field.setRequired(true);

                } else if (inputs.get(i).hasAttr("data-validate")) {

                    field.setRequired(true);

                } else {
                    field.setRequired(false);
                }

                fields.add(field);
            }
            formFound = new Form();
            formFound.setButton(formButton);
            formFound.setFields(fields);
            formFound.setFormId(formObject.attr("id"));

        } catch (IOException ex) {
            Logger.getLogger(FormElements.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(FormElements.class.getName()).log(Level.SEVERE, null, ex);

        }
        // formFound.setValidationElement("ctl00__bodyContent__valSum");// VALIDATION ELEMENT WHERE AN ERROR MESSAGE WILL BE DISPLAYED
        return formFound;
    }

    public static void checkElement(String name, Element elem) {
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }
}
