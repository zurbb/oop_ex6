package opp.ex6.main;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Sjavac {
    private static final String emptyLinePattern = "^\\s*$";
    private static Pattern commentPattern = Pattern.compile("^//.*$");
    public static void main(String[] args) {
//        TODO: maybe args 1 ? 
//        int result = readFileAndValidate(args[0]);
//        System.out.println(result);
        readFileAndValidate("tests/testCommentAndEmptyLine_shouldPass");
        }

    private static int readFileAndValidate(String fileNamePath) {
        String fileAsString = FileTextToString(fileNamePath);//file to text
        System.out.println(fileAsString);
        int isMethodsAndVariablesStatmementsValid = readMethodsAndVariables(fileAsString);
        if(isMethodsAndVariablesStatmementsValid==0){
            /// validate all
        }
        else{
            return isMethodsAndVariablesStatmementsValid;
        }
        return 1;
//        int isMethodsAndVariablesValid = readMethodsAndVariables(file);
        // read all the file and only scan for variables and methods.
//        if(isMethodsAndVariablesValid==0)
//    // read in buffer from the file:
//        // for each buffer do:
//      / checks only for methods. 
    }

    private static String FileTextToString(String fileNamePath) {
        StringBuilder text = new StringBuilder();
        FileReader file = null;
        try {
            file = new FileReader(fileNamePath);
        } catch (FileNotFoundException e) {
            //todo: some breakpoint to prevent from continue and add informative massage
            System.out.println("File Not Found");
        }
        //todo: file.exists?
        if (file != null) {
            System.out.println("File opened successfully");
            //todo: some breakpoint to prevent from continue and add informative massage
        } else {
            System.out.println("File open failed");
        }
        BufferedReader reader = new BufferedReader(file);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        } catch (IOException e) {
            //todo: some breakpoint to prevent from continue and add informative massage
            System.out.println("Error reading file");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                //todo: some breakpoint to prevent from continue and add informative massage
                System.out.println("Error closing file");
            }
        }
        return text.toString();
    }

    private static int readMethodsAndVariables(String fileText) {
        return 1;
        // read ass buffer
        // if space line ->> skip
        // if comments line ->> validate and skip
        // if variable ->> call variableValidator
        // if there is { in the line ->> find the closing one and concatenate all the string together  and call the methodvalidator
    }

    private static void tests(){
        List<String> files = new ArrayList<String>();
        files.add("testCommentAndEmptyLine_shouldPass");
//        for(String file : files){
//            System.out.println("file: " + file +readFileAndValidate(file).toString());
//        }

    }
}
