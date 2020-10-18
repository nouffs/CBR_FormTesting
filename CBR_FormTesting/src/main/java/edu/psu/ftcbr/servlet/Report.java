/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.servlet;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.tools.sjavac.Log;
import edu.psu.ftcbr.valueobject.Field;
import edu.psu.ftcbr.valueobject.Form;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

@WebServlet("/Report")
public class Report extends HttpServlet{
	private static final long serialVersionUID = 1L;
 private List<Field> fields = new ArrayList<Field>();

//invoked from doGet method to create PDF through servlet 
 public Report() {
        super();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                OutputStream out=response.getOutputStream();

             try {
        
        HttpSession session = request.getSession(true);
        Form form = (Form) session.getAttribute("form");
         fields= form.getFields();
        //Set content type to application / pdf
        //browser will open the document only if this is set
        response.setContentType("application/pdf");
        //Get the output stream for writing PDF object        
   
            Document  document = new Document(PageSize.A4);
                    //getDocument();
 DecimalFormat df = new DecimalFormat("0.00");
    //special font sizes
   Font bfBold12 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0));
   
      Font failFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
      Font passFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN);

   Font bf12 = new Font(FontFamily.HELVETICA, 12); 
            /* Basic PDF Creation inside servlet */
            PdfWriter.getInstance(document, out);
           document.open();


//create a paragraph
DateFormat dformat;
                    dformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
Date dateobj = new Date();
   Paragraph paragraph = new Paragraph("This report provides information obtained through automated form testing." + "\r\n Created on: "+ dformat.format(dateobj));

    
   //specify column widths
   float[] columnWidths = {1.5f, 2f, 5f, 2f};
   //create PDF table with the given widths
   PdfPTable table = new PdfPTable(columnWidths);
   // set table width a percentage of the page width
   table.setWidthPercentage(90f);
 
   //insert column headings
if (fields == null){
   document.add(new Paragraph("Report can't be loaded, please make sure that a URL has been inserted through: http://localhost:8080/CBR_FormTesting."));
 document.close();
}
else{
   for (int i = 0 ; i < fields.size(); i ++){
       insertCell(table, "Test-Cases for testing ("+ fields.get(i).getName() + ") Field", Element.ALIGN_LEFT, 3, bfBold12,false);
   insertCell(table, "Testing Result", Element.ALIGN_LEFT, 1, bfBold12,false);
  
    table.setHeaderRows(1);
   
       for (int j = 0 ; j < fields.get(i).getTestCases().size(); j ++){
         insertCell(table, fields.get(i).getTestCases().get(j).getDescription(), Element.ALIGN_LEFT, 3, bf12,false);
      insertCell(table, fields.get(i).getTestCases().get(j).isCasePassed()? "PASS":"FAIL", Element.ALIGN_CENTER, 3, fields.get(i).getTestCases().get(j).isCasePassed()? passFont:failFont ,false);
         System.out.println("Report:::"+fields.get(i).getName() + "PASSED???" +fields.get(i).getTestCases().get(j).isCasePassed());
      
       Logger.getLogger(Report.class.getName()).log(WARNING, "Report:::"+fields.get(i).getName() +" CASE:: "+fields.get(i).getTestCases().get(j).getDescription()+ " PASSED???" +fields.get(i).getTestCases().get(j).isCasePassed());

       }
          //insert an empty row
   insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12,true);  

   }
  
   
 
    
   //add the PDF table to the paragraph 
   paragraph.add(table);
   // add the paragraph to the document
   document.add(paragraph);



           // document.add((Element) getDocument());
           // document.add(new Paragraph("PDF Created Using Servlet, iText Example Works"));
           // document.close();
           document.close();}
        }
                catch (DocumentException exc){
                    log("", exc);
                }catch(Exception e){
                 log("", e);}
             
        finally {            
            out.close();
        }
    }


    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
    

    
    
     private void insertCell(PdfPTable table, String text, int align, int colspan, Font font, boolean isempty){
   //

  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  
  if (isempty){
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
  cell.setBorderColor(BaseColor.BLACK);}
  
  else{
       cell.setPaddingTop(4);
  cell.setPaddingBottom(4);}
  
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
   
 }

 
     
}
