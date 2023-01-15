package opp.ex6.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> allFileAsLineList(String fileText){
        return Arrays.asList(fileText.split("\n"));
    }

    public static List<String> subLinesOfFunction(int startPosInFile, int endPosInFile, List<String> allFileAsLineList){


        return allFileAsLineList.subList(startPosInFile, endPosInFile);
    }

    public static String findSubStringOfBrackets(String fullLine){
        int openBracketLine = fullLine.indexOf('(');
        int closeBracketLine = fullLine.indexOf(')');
        return fullLine.substring(openBracketLine + 1, closeBracketLine);
    }

    public static boolean is2TypesMatch(String value, String otherValue){
        if(RegexUtils.BOOLEAN_VALUE.matcher(value).matches()&&RegexUtils.BOOLEAN_VALUE.matcher(otherValue).matches()||
                RegexUtils.INT_VALUE.matcher(value).matches()&&RegexUtils.INT_VALUE.matcher(otherValue).matches()||
                RegexUtils.CHAR_VALUE.matcher(value).matches()&&RegexUtils.CHAR_VALUE.matcher(otherValue).matches()||
                RegexUtils.DOUBLE_VALUE.matcher(value).matches()&&RegexUtils.DOUBLE_VALUE.matcher(otherValue).matches()||
                RegexUtils.STRING_VALUE.matcher(value).matches()&&RegexUtils.STRING_VALUE.matcher(otherValue).matches()){
            return true;
        }
        return false;
    }

    public static boolean isValidType(String type){
        if(RegexUtils.BOOLEAN_VALUE.matcher(type).matches()||
                RegexUtils.INT_VALUE.matcher(type).matches()||
                RegexUtils.CHAR_VALUE.matcher(type).matches()||
                RegexUtils.DOUBLE_VALUE.matcher(type).matches()||
                RegexUtils.STRING_VALUE.matcher(type).matches()){
            return true;
        }
        return false;
    }




}
