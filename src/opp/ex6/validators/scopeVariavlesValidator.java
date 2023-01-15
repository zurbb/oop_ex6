package opp.ex6.validators;

import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.utils.RegexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class scopeVariavlesValidator {
    private List<Map<String,Variable>> scopesVariables = new ArrayList<>();
    private int scope = 0;

    scopeVariavlesValidator(){
        openScope();
    }

    public void openScope(){
        scopesVariables.add(new HashMap<>());
        scope+=1;
    }

    public void removeScope(){
        scope-=1;
        scopesVariables.remove(scope);
    }

    public void validate(String varLine) throws BaseException {

        if(!varLine.endsWith(";")){
            throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine);
        }
        varLine  = varLine.trim();

        if(RegexUtils.START_WITH_TYPE.matcher(varLine).find()){
            Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
            if(typeMatcher.find()){
                String type = typeMatcher.group(1);
                varLine = varLine.replaceFirst(type,"");
                varLine = varLine.replaceFirst(";","").trim();
                varLine+=",";
                // todo: chNGE WITH == AND WITHOUUT
                if(RegexUtils.VARIABLES.matcher(varLine).find()){
                    String[] namesAndValues  = varLine.split(",");
                    for(String nameAndValue : namesAndValues){
                        if(nameAndValue.contains("=")){
                            String[] split =  nameAndValue.split("=");
                            String name = split[0].trim(), value = split[1].trim();
//                            addVarAndValidate(type,name,value);
                        }
                        else{
//                            addVarAndValidate(type,nameAndValue.trim());
                        }
                    }
                }
                return;

            }
        }
        else if(RegexUtils.VARIABLE_ONLY_ONE_ASSIGMENT.matcher(varLine).find()){
            varLine = varLine.replaceFirst(";","").trim();
            String[] split =  varLine.split("=");
            String name = split[0].trim(), value = split[1].trim();
//            addVarlWithoutTypeAndValidate(name,value);
            return;
        }

        throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine);

    }
}

