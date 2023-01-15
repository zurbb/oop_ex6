package opp.ex6.Validators;



///^(\s*)(final)*(\s*)(int|double|boolean|char|String)+(\s*)([a-z]+[a-z0-9]*)(\s*=\s*)('.'|".*"|0|[1-9][0-9]*|true|false|[0-9]+\.[0-9]+)(;$)/gm

import opp.ex6.Types;

import java.util.*;
import java.util.regex.Matcher;

public class VariablesValidator {
    // for example "final String a =2;" ->> <"a",Variable.of(a)>

    private static final Set<String> IllegalNames = new HashSet<>(Arrays.asList(Types.STRING, Types.BOOLEAN, Types.CHAR, Types.DOUBLE, Types.INT));
    private static Map<String, Variable> globalVariables= new HashMap<>();


    public static void validateGlobal(String varLine) throws BaseException{
        if(!varLine.endsWith(";")){
        throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine);
        }
        varLine  = varLine.trim();
        if(varLine.startsWith("final ")){
            varLine = varLine.replaceFirst("final","");
            Matcher typeMatcher = Const.TYPE.matcher(varLine);
            if(typeMatcher.find()){
                String type = typeMatcher.group(1);
                varLine = varLine.replaceFirst(type,"");
                varLine = varLine.replaceFirst(";","").trim();
                varLine+=",";
                if(Const.VARIABLES_MUST_BE_WITH_EQUALS_SIGN.matcher(varLine).find()){
                    String[] namesAndValues  = varLine.split(",");
                    for(String nameAndValue : namesAndValues){
                        String[] split =  nameAndValue.split("=");
                        String name = split[0].trim(), value = split[1].trim();
                        addGlobalFinalAndValidate(type,name,value);
                    }
                    return;
                }

            }

        }
        else if(Const.START_WITH_TYPE.matcher(varLine).find()){
            Matcher typeMatcher = Const.TYPE.matcher(varLine);
            if(typeMatcher.find()){
                String type = typeMatcher.group(1);
                varLine = varLine.replaceFirst(type,"");
                varLine = varLine.replaceFirst(";","").trim();
                varLine+=",";
                // todo: chNGE WITH == AND WITHOUUT
                if(Const.VARIABLES.matcher(varLine).find()){
                    String[] namesAndValues  = varLine.split(",");
                    for(String nameAndValue : namesAndValues){
                        if(nameAndValue.contains("=")){
                            String[] split =  nameAndValue.split("=");
                            String name = split[0].trim(), value = split[1].trim();
                            addGlobalAndValidate(type,name,value);
                        }
                        else{
                            addGlobalAndValidate(type,nameAndValue.trim());
                        }
                    }
                }
                return;

            }
        }
        else if(Const.VARIABLE_ONLY_ONE_ASSIGMENT.matcher(varLine).find()){
            varLine = varLine.replaceFirst(";","").trim();
            String[] split =  varLine.split("=");
            String name = split[0].trim(), value = split[1].trim();
            addGlobalWithoutTypeAndValidate(name,value);
            return;
        }

        throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine);

    }


    private static void addGlobalVariable(String name, Variable variable){
        globalVariables.put(name,variable);
    }

    private static void addGlobalWithoutTypeAndValidate(String name, String value) throws BaseException{
        verifyAlreadyDefined(name);
        verifyName(name);
        Variable variable = globalVariables.get(name);
        if(variable.isFinal){
            throw new VerifierExceptions.CannotModifieFinal(name +"=" + value);
        }
        verifyTypeMatchValue(variable.getType(),value);
        globalVariables.get(name).initiate();
        addGlobalVariable(name,variable);
    }

    private static void addGlobalFinalAndValidate(String type, String name, String value) throws BaseException{
        verifyNotAlreadyDefined(name);
        verifyName(name);
        verifyType(type);
        verifyTypeMatchValue(type,value);
        Variable variable = new Variable(true,type,name,value);
        addGlobalVariable(name,variable);
    }

    private static void addGlobalAndValidate(String type, String name, String value) throws BaseException{
        verifyNotAlreadyDefined(name);
        verifyName(name);
        verifyType(type);
        verifyTypeMatchValue(type,value);
        Variable variable = new Variable(false,type,name,value);
        addGlobalVariable(name,variable);
    }
    private static void addGlobalAndValidate(String type, String name) throws BaseException{
        verifyNotAlreadyDefined(name);
        verifyName(name);
        verifyType(type);
        Variable variable = new Variable(false,type,name);
        addGlobalVariable(name,variable);
    }

    private static void verifyNotAlreadyDefined(String name)throws VerifierExceptions.alreadyDefined {
        if(globalVariables.containsKey(name)){
            throw new VerifierExceptions.alreadyDefined(name);
        }
    }
    private static void verifyAlreadyDefined(String name) throws VerifierExceptions.VariableNotDefined{
        if(!globalVariables.containsKey(name)){
            throw new VerifierExceptions.VariableNotDefined(name);
        }
    }
    private static void verifyName(String name) throws VerifierExceptions.IllegalName{
        if(!Const.NAME.matcher(name).find() && !IllegalNames.contains(name)){
            if(name.length()==0){
                name = "<empty>";
            }
            throw new VerifierExceptions.IllegalName(name);
        }

    }
    private static void verifyType(String type) throws VerifierExceptions.IllegalType{
        if(!Const.TYPE.matcher(type).find()){
            throw new VerifierExceptions.IllegalType(type);
        }

    }


    private static void verifyTypeMatchValue(String type, String value) throws VerifierExceptions.ValueNotMatchToType{
        // if the value already defined
        if(globalVariables.containsKey(value)){
            Variable other = globalVariables.get(value);
            if(type.equals(other.getType()) && other.isInitiated()){
                return;
            }
        }
        // int
        else if(type.equals(Types.INT) && Const.INT_VALUE.matcher(value).find()){
            return;
        }
        // double
        else if(type.equals(Types.DOUBLE) && Const.DOUBLE_VALUE.matcher(value).find()){
            return;
        }
        // boolean
        else if(type.equals(Types.BOOLEAN) && Const.BOOLEAN_VALUE.matcher(value).find()){
            return;
        }
        //char
        else if(type.equals(Types.CHAR) && Const.CHAR_VALUE.matcher(value).find()){
            return;
        }
        //string
        else if(type.equals(Types.STRING) && Const.STRING_VALUE.matcher(value).find()) {
            return;
        }

        throw new VerifierExceptions.ValueNotMatchToType(type,value);


    }




}
