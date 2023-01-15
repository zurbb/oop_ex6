package opp.ex6.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.utils.RegexUtils;
import opp.ex6.utils.Utils;

public class MethodValidator {

    private static Map<String, Method> globalMethods= new HashMap<>();

    public static Map<String, Method> getGlobalMethods(){
        return globalMethods;
    }



    public static boolean isNameAlreadyDefined(String methodName){
        return globalMethods.containsKey(methodName);
    }





    public static void validateMethods(String fileText) throws BaseException{
        List<String> allFileAsLineList = Utils.allFileAsLineList(fileText);

        for(Method method: getGlobalMethods().values()){
            int start = method.getStartPosInd() ,end = method.getEndPosInd();
            List<String> allLinesOfMethod = Utils.subLinesOfFunction(start,end,allFileAsLineList);
            method.validate(allLinesOfMethod);
        }

    }

}
