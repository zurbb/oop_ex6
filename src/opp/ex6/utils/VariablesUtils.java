//package opp.ex6.utils;
//
//import opp.ex6.exception.VerifierExceptions;
//import opp.ex6.validators.Variable;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//
//public class VariablesUtils {
//
//
//public List<Variable> getVariablesFromLine(String varLine,  int lineIndex){
//    List<Variable> variableList = new ArrayList<>();
//    varLine = varLine.trim();
//
//    boolean flagFinal = false;
//    if(varLine.startsWith("final ")){
//        flagFinal = true;
//        varLine = varLine.replaceFirst("final","");
//    }
//
//    Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
//    String type = "";
//    if(typeMatcher.find()){
//        type = typeMatcher.group(1);
//        varLine = varLine.replaceFirst(type,"");
//        varLine = varLine.replaceFirst(";","").trim();
//        varLine+=",";
//    }
//
//
//    if (flagFinal){
//        if(RegexUtils.VARIABLES_MUST_BE_WITH_EQUALS_SIGN.matcher(varLine).find()){
//            String[] namesAndValues  = varLine.split(",");
//            for(String nameAndValue : namesAndValues){
//                String[] split =  nameAndValue.split("=");
//                String name = split[0].trim(), value = split[1].trim();
//
//                variableList.add(new Variable(true,type,name, value););
//            }
//        }
//    }
//    else{
//        if(RegexUtils.VARIABLES.matcher(varLine).find()){
//            String[] namesAndValues  = varLine.split(",");
//            for(String nameAndValue : namesAndValues){
//                if(nameAndValue.contains("=")){
//                    String[] split =  nameAndValue.split("=");
//                    String name = split[0].trim(), value = split[1].trim();
//                    variableList.add(new Variable(false,type,name,value);
//
//                }
//                else{
//                    variableList.add(new Variable(type,nameAndValue.trim(),false));
//                }
//            }
//
//        }
//        else if(RegexUtils.VARIABLE_ONLY_ONE_ASSIGMENT.matcher(varLine).find()){
//            varLine = varLine.replaceFirst(";","").trim();
//            String[] split =  varLine.split("=");
//            String name = split[0].trim(), value = split[1].trim();
//            addGlobalWithoutTypeAndValidate(name,value,lineIndex);
//            return;
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//    if(varLine.startsWith("final ")){
//        flagFinal= true;
//        varLine = varLine.replaceFirst("final","");
//        Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
//        if(typeMatcher.find()){
//            String type = typeMatcher.group(1);
//            varLine = varLine.replaceFirst(type,"");
//            varLine = varLine.replaceFirst(";","").trim();
//            varLine+=",";
//            if(RegexUtils.VARIABLES_MUST_BE_WITH_EQUALS_SIGN.matcher(varLine).find()){
//                String[] namesAndValues  = varLine.split(",");
//                for(String nameAndValue : namesAndValues){
//                    String[] split =  nameAndValue.split("=");
//                    String name = split[0].trim(), value = split[1].trim();
//                    addGlobalAndValidate(type,name,value, lineIndex, true);
//                }
//                return;
//            }
//
//        }
//
//    }
//    else if(RegexUtils.START_WITH_TYPE.matcher(varLine).find()){
//        Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
//        if(typeMatcher.find()){
//            String type = typeMatcher.group(1);
//            varLine = varLine.replaceFirst(type,"");
//            varLine = varLine.replaceFirst(";","").trim();
//            varLine+=",";
//            // todo: chNGE WITH == AND WITHOUUT
//            if(RegexUtils.VARIABLES.matcher(varLine).find()){
//                String[] namesAndValues  = varLine.split(",");
//                for(String nameAndValue : namesAndValues){
//                    if(nameAndValue.contains("=")){
//                        String[] split =  nameAndValue.split("=");
//                        String name = split[0].trim(), value = split[1].trim();
//                        addGlobalAndValidate(type,name,value,lineIndex,false);
//                    }
//                    else{
//                        addGlobalAndValidate(type,nameAndValue.trim());
//                    }
//                }
//            }
//            return;
//
//        }
//    }
//    else if(RegexUtils.VARIABLE_ONLY_ONE_ASSIGMENT.matcher(varLine).find()){
//        varLine = varLine.replaceFirst(";","").trim();
//        String[] split =  varLine.split("=");
//        String name = split[0].trim(), value = split[1].trim();
//        addGlobalWithoutTypeAndValidate(name,value,lineIndex);
//        return;
//    }
//
//    throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine);
//}
//}