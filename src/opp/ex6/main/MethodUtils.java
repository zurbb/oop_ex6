package opp.ex6.main;

import opp.ex6.Validators.*;

import java.util.*;

public class MethodUtils {

    public static boolean validateMethodSignature(String methodLine){
        return Const.METHOD_PATTERN_SIGNATURE.matcher(methodLine).find();
    }

    public static String getMethodName(String methodLine) throws  VerifierExceptions.alreadyDefined{
        // first validate name and if false throw VerifierExceptions.IllegalName
        methodLine = methodLine.trim();
        int openBracketLine = methodLine.indexOf('(');
        String name=  methodLine.substring(0,openBracketLine).split("\\s")[1];
        if(Method.isNameAlreadyDefined(name)){
            throw new VerifierExceptions.alreadyDefined(name);
        }
        return name;
    }

    private static String findSubString(String fullLine){
        int openBracketLine = fullLine.indexOf('(');
        int closeBracketLine = fullLine.indexOf(')');
        return fullLine.substring(openBracketLine + 1, closeBracketLine);
    }
    public static List<Variable> getMethodParams(String methodLine) throws  VerifierExceptions.MethodParamInvalid{
        List<Variable> parameters = new ArrayList<>();
        String methodArguments = findSubString(methodLine);
        if(methodArguments.trim().isEmpty()){
            return parameters;
        }
        if (!Const.METHOD_PATTERN_ARGUMENTS.matcher(methodArguments+",").matches()){
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

    public static Map.Entry<Integer,Integer> forwardScanner(Scanner scanner, int start) throws BaseException {
        System.out.println("------------------------------------");
        int brackets = 1;
        int endLine = start;
        String line="";
        String prevLine ="";
        while (scanner.hasNextLine()&&brackets>0){
            endLine++;
            prevLine = line;
            line = scanner.nextLine();
            if (line.contains("{")){
                if (Const.IF_WHILE_PATTERN.matcher(line).matches()){
                    String arguments = (findSubString(line)+"||").trim();
                    if (Const.IF_WHILE_ARGUMENTS.matcher(arguments).matches()){
                        brackets++;
                    }
                    else{
                        brackets = -1;
                    }
                }
                else{
                    brackets = -1;
                }
            }
            if (line.contains("}")){
                if(line.trim().equals("}")){
                    brackets--;
                }
                else{
                    throw new VerifierExceptions.InvalidMethodCloseBracket("line: ",endLine);
                }
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
