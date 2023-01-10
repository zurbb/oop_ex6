package opp.ex6.main;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

//
//0 – if the code is legal.
//• 1 – if the code is illegal.
//• 2 – in case of IO errors (see errors).
public class Sjavac {
    private static final String emptyLinePattern = "^\\s*$";
    private static final String COMMENT_LINE_START_WITH_SPACES = "comment line start with spaces, but got: ";
    private static final String ERROR_READING_FILE = "Error reading file";
    private static final String ERROR_CLOSING_FILE = "Error closing file";
    private static final String FILE_OPEN_FAILED = "File open failed";
    private static final String FILE_NOT_FOUND = "File Not Found";
    private static Pattern commentPattern = Pattern.compile("^//.*$");
    private static Pattern illegalulCommentPattern = Pattern.compile("^[\\s]+//.*$");
    public static void main(String[] args) {
//        TODO: maybe args 1 ? 
//        int result = readFileAndValidate(args[0]);
//        System.out.println(result);
        tests();
//        readFileAndValidate("tests/testCommentAndEmptyLine_shouldPass");
        }

    private static int readFileAndValidate(String fileNamePath) {
        String fileAsString = "";
        try {
            fileAsString = FileTextToString(fileNamePath);
        }
        catch (IOException e){
            return 2;
        }
        //file to text
        int isMethodsAndVariablesStatmementsValid = readMethodsAndVariables(fileAsString);
        if(isMethodsAndVariablesStatmementsValid==0){
            System.out.println("all valid");
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

    private static String FileTextToString(String fileNamePath) throws IOException {
        StringBuilder text = new StringBuilder();
        FileReader file = null;
        try {
            file = new FileReader(fileNamePath);
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
            throw  new IOException();
        }
        //todo: file.exists?
        if (file == null) {
            System.err.println(FILE_OPEN_FAILED);
            throw  new IOException();
        }
        BufferedReader reader = new BufferedReader(file);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        } catch (IOException e) {
            System.err.println(ERROR_READING_FILE);
            throw  new IOException();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(ERROR_CLOSING_FILE);
                throw  new IOException();
            }
        }
        return text.toString();
    }

    private static int readMethodsAndVariables(String fileText) {

        Scanner scanner = new Scanner(fileText);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // in case of legal comment or empty line
            if(line.matches(emptyLinePattern) && commentPattern.matcher(line).find()){
                continue;
            }
            else if (illegalulCommentPattern.matcher(line).find()){
                System.err.println(COMMENT_LINE_START_WITH_SPACES + line);
                return 1;
            }
        }
        return 0;
    }

    private static void tests(){
        List<String> files = new ArrayList<String>();
        files.add("tests/testCommentAndEmptyLine_shouldPass");
        files.add("tests/testIllegalCommentShouldFail");

        for(String file : files){
            readFileAndValidate(file);
            System.out.println("file: " + file+"\n" );
        }

    }
}


