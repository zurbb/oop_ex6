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
        int result = readFileAndValidate(args[0]);
        System.out.println(result);
    }

    private static int readFileAndValidate(String file){
        // read all the file and only scan for variables and methods. 
        int isMethodsAndVariablesValid = readMethodsAndVariables(file);
//        if(isMethodsAndVariablesValid==0)
//    // read in buffer from the file:
//        // for each buffer do:
//      / checks only for methods. 
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
