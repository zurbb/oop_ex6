package opp.ex6.utils;

import opp.ex6.exception.VerifierExceptions;

import java.util.ArrayList;
import java.util.List;

import static opp.ex6.utils.Utils.findSubStringOfBrackets;


public class IfAndWhileUtils {


    public static List<String> ifWhileArgumentsValidation(String line, int lineIndex) throws VerifierExceptions.InvalidIfWhileArguments {
        List<String> arguments = new ArrayList<>();
        String argumentsBeforeSplit = findSubStringOfBrackets(line);
        if (argumentsBeforeSplit.equals("")){
            return arguments;
        }
        else{
            argumentsBeforeSplit = (argumentsBeforeSplit+"||").trim();
            if (RegexUtils.IF_WHILE_ARGUMENTS.matcher(argumentsBeforeSplit).matches()){
                for (String value : argumentsBeforeSplit.split("(&&|\\|\\|)")) {
                    arguments.add(value.trim());
                }
            }
            else{
                //todo: change execption to add line number?
                throw new VerifierExceptions.InvalidIfWhileArguments(line, lineIndex);
            }
        }
        return arguments;
    }



}
