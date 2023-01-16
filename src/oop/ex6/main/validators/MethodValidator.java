package oop.ex6.main.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oop.ex6.main.exception.BaseException;
import oop.ex6.main.utils.Utils;

public class
MethodValidator {

    private static Map<String, Method> globalMethods= new HashMap<>();

    public static Map<String, Method> getGlobalMethods(){
        return globalMethods;
    }



    public static boolean isNameAlreadyDefined(String methodName){
        return globalMethods.containsKey(methodName);
    }


    public static void validateMethods(String fileText, ScopeVariablesValidator scopeVariablesValidator) throws BaseException{
        List<String> allFileAsLineList = Utils.allFileAsLineList(fileText);

        for(Method method: getGlobalMethods().values()){
            int start = method.getStartPosInd() ,end = method.getEndPosInd();
            List<String> allLinesOfMethod = Utils.subLinesOfFunction(start,end,allFileAsLineList);
            method.validate(allLinesOfMethod, scopeVariablesValidator);
        }

    }

    public static Method getMethodByName(String methodName){
        methodName = methodName.trim();
        if(globalMethods.containsKey(methodName)){
            return globalMethods.get(methodName);
        }
        return null;
    }

}
