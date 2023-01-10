package opp.ex6.Validators;

import opp.ex6.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class VariablesValidator {
    // for example "final String a =2;" ->> <"a",Variable.of(a)>
    private static Map<String, Variable> globalVariables= new HashMap<>();
    private Map<String, Variable> innerScopeVariables = new HashMap<>();
    private final static Pattern patternForGlobalString = Pattern.compile("/(\\s*)(int|double|boolean|char|String)(\\s*=\\s*)([\\x20-\\x2C\\x2E-\\x7E]+)");

    int x = 3;
    x= 5;
/^(\s*)(int|double|boolean|char|String)*(\s*)([a-z]+[a-z0-9]*)(\s*=\s*)([\x20-\x2C\x2E-\x7E]+;$)/gm
    public static void validateGlobal(String varLine) throws {
        if(patternForGlobalString.matcher(varLine).find()){
            varLine= varLine.replaceAll("[=;]", " ");
            String[] splited = varLine.split("\\s+");
            if()
        }
        else {
            throw
        }

    }

//    public void validateInnerScope(String varLine) throws {

//    }
}
