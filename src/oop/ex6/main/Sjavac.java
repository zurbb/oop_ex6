package oop.ex6.main;


import oop.ex6.main.utils.MethodUtils;
import oop.ex6.main.utils.RegexUtils;
import oop.ex6.main.exception.BaseException;
import oop.ex6.main.exception.VerifierExceptions;
import oop.ex6.main.validators.Method;
import oop.ex6.main.validators.MethodValidator;
import oop.ex6.main.validators.ScopeVariablesValidator;
import oop.ex6.main.validators.Variable;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//
//0 – if the code is legal.
//• 1 – if the code is illegal.
//• 2 – in case of IO errors (see errors).
public class Sjavac {

    private static final String COMMENT_LINE_START_WITH_SPACES = "comment line start with spaces, but got: ";
    private static final String ERROR_READING_FILE = "Error reading file";
    private static final String ERROR_CLOSING_FILE = "Error closing file";
    private static final String FILE_OPEN_FAILED = "File open failed";
    private static final String FILE_NOT_FOUND = "File Not Found";

    private static ScopeVariablesValidator scopeVariablesValidator ;
    public static Scanner scanner;

    public static void main(String[] args) {
        MethodValidator.reset();
        scopeVariablesValidator = new ScopeVariablesValidator();
        readFileAndValidate(args[0]);
//        readFileAndValidate("/cs/usr/itayyamin/Desktop/oop_ex6/tests/genral_tests/test501.sjava");

//        tests();
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
        try {
            readMethodsAndVariables(fileAsString);
            scopeVariablesValidator.setGlobalVariables(scopeVariablesValidator.getFirstScopeVariables());
            MethodValidator.validateMethods(fileAsString, scopeVariablesValidator);
        }
        catch (BaseException e){
            System.out.println(NOT_VALID_VALUE);
            System.err.println(e.getMessage());
            return;
        }
        System.out.println(VALID_VALUE);
    }




    private static String FileTextToString(String fileNamePath) throws IOException {
        StringBuilder text = new StringBuilder();
        FileReader file = null;
        try {
            file = new FileReader(fileNamePath);
        } catch (FileNotFoundException e) {
            throw  new IOException();
        }
        //todo: file.exists?
        if (file == null) {
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
            throw  new IOException();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw  new IOException();
            }
        }
        return text.toString();
    }

    // only read the method and variables and save the content. in case of invalid method or
    private static  void readMethodsAndVariables(String fileText) throws BaseException {
        scanner = new Scanner(fileText);
        String line;
        int lineIndex = 0;
        while (scanner.hasNextLine()) {
            lineIndex+=1;
            line = scanner.nextLine();
            // in case of legal comment or empty line
            if(RegexUtils.EMPTY_LINE_PATTERN.matcher(line).matches() || RegexUtils.COMMENT_PATTERN.matcher(line).matches()){
                continue;
            }

            // must be comment but not valid
            else if (RegexUtils.ILEGAL_COMMENT.matcher(line).matches()){
                throw new VerifierExceptions.IllegalComment(line, lineIndex);
            }
            else if (MethodUtils.validateMethodSignature(line)){
                String name= MethodUtils.getMethodName(line,lineIndex);
                List<Variable> params = MethodUtils.getMethodParams(line, lineIndex);
                Map.Entry<Integer,Integer> startAndEnd = MethodUtils.forwardScanner(scanner,lineIndex);
                lineIndex = startAndEnd.getValue();
                Method.addMethod(startAndEnd.getKey(), startAndEnd.getValue(), name,params);
                continue;
            }
            scopeVariablesValidator.validate(line, lineIndex);
        }

    }
}


