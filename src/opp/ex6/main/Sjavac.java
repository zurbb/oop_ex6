package opp.ex6.main;


public class Sjavac {
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

    private static int readMethodsAndVariables(String file) {
        // read ass buffer
        // if space line ->> skip
        // if comments line ->> validate and skip
        // if variable ->> call variableValidator
        // if there is { in the line ->> find the closing one and concatenate all the string together  and call the methodvalidator
    }
}
