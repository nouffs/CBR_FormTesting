/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.ftcbr.utilities;

import edu.psu.ftcbr.valueobject.TestCase;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CSVReadWrite {

   

    public static boolean insertCase(TestCase onecase) {

        boolean success = false;

        try {

            List<List<String>> rows = Arrays.asList(
                    Arrays.asList(onecase.getCaseId(), onecase.getFieldName(), onecase.getDescription(), onecase.getValue())
            );

            FileWriter csvWriter;

            csvWriter = new FileWriter("cases.csv", true);

//csvWriter.append("CaseID");
//csvWriter.append(",");
//csvWriter.append("Case_Text");
//csvWriter.append(",");
//csvWriter.append("Description");
//csvWriter.append(",");
//csvWriter.append("Value");
//csvWriter.append("\n");
            for (List<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            success = true;
        } catch (IOException ex) {
            Logger.getLogger(CSVReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public static List<TestCase> readTestCasesFromCSV() {
        List<TestCase> testCases = new ArrayList<TestCase>();
        Path pathToFile = Paths.get("cases.csv");

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine(); //SKIP HEADER
            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                TestCase oneCase = createTestCase(attributes);

                // adding testCase into ArrayList
                testCases.add(oneCase);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return testCases;
    }
    
    
    
//creates a test case object from given csv data
    private static TestCase createTestCase(String[] metadata) {
        TestCase test = new TestCase();

        test.setCaseId(metadata[0]);
        test.setFieldName(metadata[1]);
        test.setDescription(metadata[2]);
        test.setValue(metadata[3]);

        return test;
    }

}
