package opp.ex6.utils;

import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.validators.*;

import java.util.*;

import static opp.ex6.utils.Utils.findSubStringOfBrackets;

public class MethodUtils {

    public static boolean validateMethodSignature(String methodLine){
        return RegexUtils.METHOD_PATTERN_SIGNATURE.matcher(methodLine).find();
    }

    public static String getMethodName(String methodLine) throws  VerifierExceptions.alreadyDefined{
        // first validate name and if false throw VerifierExceptions.IllegalName
        methodLine = methodLine.trim();
        int openBracketLine = methodLine.indexOf('(');
        String name=  methodLine.substring(0,openBracketLine).split("\\s")[1];
        //todo fix!!!
        if(MethodValidator.isNameAlreadyDefined(name)){
            throw new VerifierExceptions.alreadyDefined(name);
        }
        return name;
    }


    public static List<Variable> getMethodParams(String methodLine) throws  VerifierExceptions.MethodParamInvalid{
        List<Variable> parameters = new ArrayList<>();
        String methodArguments = findSubStringOfBrackets(methodLine);
        if(methodArguments.trim().isEmpty()){
            return parameters;
        }
        if (!RegexUtils.METHOD_PATTERN_ARGUMENTS.matcher(methodArguments+",").matches()){
            throw new VerifierExceptions.MethodParamInvalid(methodArguments);
        }
        HashSet<String> functionParamNames = new HashSet<>();

        String[] vars = methodArguments.split(",");
        for(String typeAndName : vars){
            String[] typeAndNameSplit = typeAndName.split("\\s");
            String type = typeAndNameSplit[0].trim(), name = typeAndNameSplit[1].trim();
            if (functionParamNames.contains(name)){
                throw new VerifierExceptions.MethodParamInvalid(methodArguments);
            }
            functionParamNames.add(name);
            parameters.add(new Variable(type,name,true));
        }
        return parameters;
    }

    //    if(....){




    public static Map.Entry<String,List<String>> functionCallArgumentsValidation(String line){
        line = line.trim();
        String name = line.split(" ")[0];
        List<String> arguments = new ArrayList<>();
        String argumentsBeforeSplit = findSubStringOfBrackets(line);
        if (argumentsBeforeSplit.equals("")){
            return Map.entry(name,arguments);
        }
        else{
            for (String value : argumentsBeforeSplit.split(",")) {
                arguments.add(value.trim());
            }
            for(String argument: arguments){
                checkValidArgumentPattern(argument);
            }
        }
        return Map.entry(name,arguments);
    }
    //foo(x)

    private static void checkValidArgumentPattern(String argument) {
        if (RegexUtils.BOOLEAN_VALUE.matcher(argument).matches()){
            return;
        }
        if (RegexUtils.INT_VALUE.matcher(argument).matches()){
            return;
        }
        if (RegexUtils.DOUBLE_VALUE.matcher(argument).matches()){
            return;
        }
        if (RegexUtils.STRING_VALUE.matcher(argument).matches()){
            return;
        }
        if (RegexUtils.CHAR_VALUE.matcher(argument).matches()){
            return;
        }
        if (RegexUtils.VARIABLES_NAME.matcher(argument).matches()){
            return;
        }
        //todo: change exception
        throw new RuntimeException();
    }

    public static Map.Entry<Integer,Integer> forwardScanner(Scanner scanner, int start) throws BaseException {
        int brackets = 1;
        int endLine = start;
        String line="";
        String prevLine ="";
        while (scanner.hasNextLine()&&brackets>0){
            endLine++;
            prevLine = line;
            line = scanner.nextLine();

            //String x = {
//                if (Const.IF_WHILE_PATTERN.matcher(line).matches()){
//                    String arguments = (findSubStringOfBrackets(line)+"||").trim();
//                    if (Const.IF_WHILE_ARGUMENTS.matcher(arguments).matches()){
//                        brackets++;
//                    }
//                    else{
//                        brackets = -1;
//                    }
//                }
//                else{
//                    brackets = -1;
//                }
//                Map.Entry<Boolean,List<String>> isLegalIfWhile = ifWhileArgumentsValidation(line);
            if (RegexUtils.IF_WHILE_PATTERN.matcher(line).matches()){
                brackets++;
            }
            if(line.trim().equals("}")){
                brackets--;
            }
        }
        line = line.trim();
        if (brackets!=0||!line.equals("}")){
            throw new VerifierExceptions.InvalidMethodCloseBracket(line,endLine);
        }
        if(!prevLine.trim().equals("return;")){
            throw new VerifierExceptions.MissingReturnStatement(endLine);
        }

        return Map.entry(start,endLine);
    }





}
