package opp.ex6.main;


import opp.ex6.utils.MethodUtils;
import opp.ex6.utils.RegexUtils;
import opp.ex6.utils.Utils;
import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.validators.*;

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

    private final  static ScopeVariablesValidator scopeVariablesValidator = new ScopeVariablesValidator();
    public static Scanner scanner;

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
        try {
            readMethodsAndVariables(fileAsString);
            MethodValidator.validateMethods(fileAsString, scopeVariablesValidator);
        }
        catch (BaseException e){
            //TODO:
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
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
                throw new VerifierExceptions.IllegalComment(line);
            }
            else if (MethodUtils.validateMethodSignature(line)){
                String name= MethodUtils.getMethodName(line,lineIndex);
                List<Variable> params = MethodUtils.getMethodParams(line);
                Map.Entry<Integer,Integer> startAndEnd = MethodUtils.forwardScanner(scanner,lineIndex);
                lineIndex = startAndEnd.getValue();
                Method.addMethod(startAndEnd.getKey(), startAndEnd.getValue(), name,params);
                continue;
            }
            GlobalVariablesValidator.validateGlobal(line);
        }

    }


    private static void tests(){


        List<String> files = new ArrayList<String>();
        files.add("tests/genral_tests/basic_fucntion_use_function_params_pass");//0
        files.add("tests/genral_tests/basic_function_should_pass");//1
        files.add("tests/genral_tests/basic_functionWithInnerScopeStatement_should_pass");//2
        files.add("tests/genral_tests/basicFunction_withWrongParam_shouldFail");//3
        files.add("tests/genral_tests/functionWithCallForOnther_shouldPass");//4
        files.add("/cs/usr/itayyamin/Desktop/oop_ex6/tests/genral_tests/basic_if_while_outside_func_fail");//5
        files.add("/cs/usr/itayyamin/Desktop/oop_ex6/tests/genral_tests/baisc_if_while_test_pass");//6
        files.add("tests/genral_tests/cahngingFinalInnerScope_shouldFail");//7
        files.add("tests/genral_tests/advance_test_shouldFaile");//8
        files.add("tests/genral_tests/advance_test_should_fail");//9





        int i = 9;

////        / only on test on all file

        System.out.println("***********************************************");
        int x = readFileAndValidate(files.get(i));
        System.out.println(files.get(i));
        System.out.println("result: " + Integer.toString(x));
        System.out.println("***********************************************");

//        /////////////////////////////////////

        /// for multiple lines
//        for(int i=1;i<12;i++){
//            String file = path +Integer.toString(i)+".txt";
//
//            System.out.println("****************************************************");
//            System.out.println(file);
//            int x = readFileAndValidate(file);
//            count+=x;
//            System.out.println("result: " + Integer.toString(x) + " in line " + Integer.toString(i));
//            System.out.println("****************************************************");
//
//        }
        ///////////////////////////////////


    }
}


