package opp.ex6.main;


import java.io.*;
import java.nio.file.FileSystemAlreadyExistsException;

public class Sjavac {
    public static void main(String[] args) {
//        TODO: maybe args 1 ? 
//        int result = readFileAndValidate(args[0]);
//        System.out.println(result);
        readFileAndValidate("C:\\Users\\itayy\\Desktop\\java projects\\oop_ex6\\src\\opp\\ex6\\main\\basic_text_file.txt");
    }


//    private static String();


    private static int readFileAndValidate(String fileNamePath) {

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
                System.out.println(line);
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


        return 1;
//        int isMethodsAndVariablesValid = readMethodsAndVariables(file);
        // read all the file and only scan for variables and methods.
//        if(isMethodsAndVariablesValid==0)
//    // read in buffer from the file:
//        // for each buffer do:
//      / checks only for methods. 
    }

    private static int readMethodsAndVariables(String file) {
        System.out.println("");
        return 1;
        // read ass buffer
        // if space line ->> skip
        // if comments line ->> validate and skip
        // if variable ->> call variableValidator
        // if there is { in the line ->> find the closing one and concatenate all the string together  and call the methodvalidator
    }
}
