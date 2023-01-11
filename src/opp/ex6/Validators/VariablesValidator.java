package opp.ex6.Validators;



///^(\s*)(final)*(\s*)(int|double|boolean|char|String)+(\s*)([a-z]+[a-z0-9]*)(\s*=\s*)('.'|".*"|0|[1-9][0-9]*|true|false|[0-9]+\.[0-9]+)(;$)/gm

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class VariablesValidator {
    // for example "final String a =2;" ->> <"a",Variable.of(a)>

    private static final Set<String> IllegalNames = new HashSet<>(Arrays.asList(Types.STRING, Types.BOOLEAN, Types.CHAR, Types.DOUBLE, Types.INT));
    private static Map<String, Variable> globalVariables= new HashMap<>();
    private Map<String, Variable> innerScopeVariables = new HashMap<>();
    private final static Pattern patternForGlobalString = Pattern.compile("/(\\s*)(int|double|boolean|char|String)(\\s*=\\s*)([\\x20-\\x2C\\x2E-\\x7E]+)");


    public static void validateGlobal(String varLine) throws BaseException{
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
        else {
            throw
        }

    }

//    public void validateInnerScope(String varLine) throws {

//    }
}
