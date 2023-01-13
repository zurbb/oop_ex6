package opp.ex6.main;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> allFileAsLineList(String fileText){
        return Arrays.asList(fileText.split("\n"));
    }

    public static List<String> subLinesOfFunction(int startPosInFile, int endPosInFile, List<String> allFileAsLineList){


        return allFileAsLineList.subList(startPosInFile, endPosInFile);
    }


}
