package opp.ex6.utils;

import java.util.ArrayList;
import java.util.List;

import static opp.ex6.utils.MethodUtils.findSubString;

public class IfAndWhileUtils {


    public static List<String> ifWhileArgumentsValidation(String line){
        List<String> arguments = new ArrayList<>();
        String argumentsBeforeSplit = findSubString(line);
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
                //todo: change execption
                throw new RuntimeException();
            }
        }
        return arguments;
    }

}
