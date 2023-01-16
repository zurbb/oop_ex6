package oop.ex6.main.validators;

import oop.ex6.main.exception.BaseException;
import oop.ex6.main.exception.VerifierExceptions;
import oop.ex6.main.utils.RegexUtils;
import oop.ex6.main.utils.Types;
import oop.ex6.main.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;

public class ScopeVariablesValidator {
    private List<Map<String,Variable>> scopesVariables = new ArrayList<>();
    private Map<String,Variable> globalVariables = null;
    private int scope = -1;
    private static final Set<String> booleanTypes = new HashSet<>(Arrays.asList(Types.BOOLEAN, Types.DOUBLE, Types.INT));

    private static final Set<String> IllegalNames = new HashSet<>(Arrays.asList(Types.STRING, Types.BOOLEAN, Types.CHAR, Types.DOUBLE, Types.INT));

    public void setGlobalVariables(Map<String,Variable> globalVariables){
        this.globalVariables = globalVariables;
    }

    public Map<String,Variable> getFirstScopeVariables(){
        return Utils.createDeepCopy(scopesVariables.get(0));
    }


    public ScopeVariablesValidator(){
        openScope();
    }

    public void openScope(){
        scopesVariables.add(new HashMap<>());
        scope+=1;
    }

    public void removeScope(){
        ///
        scopesVariables.remove(scope);
        scope-=1;
        if (scope==0){
            scopesVariables.set(0,Utils.createDeepCopy(globalVariables));
        }
        ////
    }

    public void validate(String varLine, int lineIndex) throws BaseException {

        if(!varLine.endsWith(";")){
            throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine, lineIndex);
        }
        varLine  = varLine.trim();
        if(varLine.startsWith("final ")){
            varLine = varLine.replaceFirst("final","");
            Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
            if(typeMatcher.find()){
                String type = typeMatcher.group(1);
                varLine = varLine.replaceFirst(type,"");
                varLine = varLine.replaceFirst(";","").trim();
                varLine+=",";
                if(RegexUtils.VARIABLES_MUST_BE_WITH_EQUALS_SIGN.matcher(varLine).find()){
                    String[] namesAndValues  = varLine.split(",");
                    for(String nameAndValue : namesAndValues){
                        String[] split =  nameAndValue.split("=");
                        String name = split[0].trim(), value = split[1].trim();
                        addVarAndValidate(type,name,value,lineIndex ,true);
                    }
                    return;
                }

            }

        }
        else if(RegexUtils.START_WITH_TYPE.matcher(varLine).find()){
            Matcher typeMatcher = RegexUtils.TYPE.matcher(varLine);
            if(typeMatcher.find()){
                String type = typeMatcher.group(1);
                varLine = varLine.replaceFirst(type,"");
                varLine = varLine.replaceFirst(";","").trim();
                varLine+=",";
                if(RegexUtils.VARIABLES.matcher(varLine).find()){
                    String[] namesAndValues  = varLine.split(",");
                    for(String nameAndValue : namesAndValues){
                        if(nameAndValue.contains("=")){
                            String[] split =  nameAndValue.split("=");
                            String name = split[0].trim(), value = split[1].trim();
                            addVarAndValidate(type,name,value,lineIndex ,false);
                        }
                        else{
                            addVarWithoutValueAndValidate(type,nameAndValue.trim(), lineIndex,false);

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
            addVarWithoutTypeAndValidate(name,value, lineIndex);
            return;
        }

        throw new VerifierExceptions.NotInPatternOfGlobalVariable(varLine, lineIndex);

    }

    private void addVarWithoutTypeAndValidate(String name, String value, int lineIndex) throws BaseException{
        verifyAlreadyDefined(name,lineIndex);
        verifyName(name,lineIndex);
        Variable variable = getClosestVariableByName(name);
        if(variable.isFinal()){
            throw new VerifierExceptions.CannotModifieFinal(name +"=" + value, lineIndex);
        }
        verifyTypeMatchValue(variable.getType(),value, lineIndex);
        variable.initiate();
//        addGlobalVariable(name,variable);
    }

    private void addVarWithoutValueAndValidate(String type, String name, int lineIndex, boolean isFinal) throws BaseException{
        verifyNotAlreadyDefined(name, lineIndex);
        verifyName(name, lineIndex);
        verifyType(type, lineIndex);
        Variable variable = new Variable(false,type,name);
        addVariableToScope(name,variable);
    }

    private void addVarAndValidate(String type, String name, String value, int lineIndex, boolean isFinal) throws BaseException{
        verifyNotAlreadyDefined(name, lineIndex);
        verifyName(name, lineIndex);
        verifyType(type, lineIndex);
        verifyTypeMatchValue(type,value, lineIndex);
        Variable variable = new Variable(isFinal,type,name,value);
        addVariableToScope(name,variable);
    }

    private void addVariableToScope(String name, Variable variable) {
        scopesVariables.get(scope).put(name,variable);
    }

    private void verifyNotAlreadyDefined(String name, int lineIndex)throws VerifierExceptions.alreadyDefined {

        if (scopesVariables.get(this.scope).containsKey(name)){
            throw new VerifierExceptions.alreadyDefined(name,lineIndex);
        }
    }
    private void verifyAlreadyDefined(String name, int lineIndex)throws VerifierExceptions.VariableNotDefined{

        if (getClosestVariableByName(name)==null){
            throw new VerifierExceptions.VariableNotDefined(name,lineIndex);
        }
    }

    private static void verifyName(String name, int lineIndex) throws VerifierExceptions.IllegalName{
        if(!RegexUtils.VARIABLES_NAME.matcher(name).find() && !IllegalNames.contains(name)){
            if(name.length()==0){
                name = "<empty>";
            }
            throw new VerifierExceptions.IllegalName(name, lineIndex);
        }

    }

    private static void verifyType(String type, int lineIndex) throws VerifierExceptions.IllegalType{
        if(!RegexUtils.TYPE.matcher(type).find()){
            throw new VerifierExceptions.IllegalType(type, lineIndex);
        }

    }

    private void verifyTypeMatchValue(String type, String value, int lineIndex) throws VerifierExceptions.ValueNotMatchToType{
        // if the value already defined in any scope
        Variable var = getClosestVariableByName(value);
        if(var!=null){
            if(var.getType().equals(type)&&var.isInitiated()){
                return;
            }
            value =var.isInitiated()? var.getType():"null";
        }
        else if(type.equals(Types.INT) && RegexUtils.INT_VALUE.matcher(value).find()||
                type.equals(Types.DOUBLE) && RegexUtils.DOUBLE_VALUE.matcher(value).find()||
                type.equals(Types.BOOLEAN) && RegexUtils.BOOLEAN_VALUE.matcher(value).find()||
                type.equals(Types.CHAR) && RegexUtils.CHAR_VALUE.matcher(value).find()||
                type.equals(Types.STRING) && RegexUtils.STRING_VALUE.matcher(value).find()){

            return;
        }

        throw new VerifierExceptions.ValueNotMatchToType(type,value, lineIndex);

    }

    /**
     * gets the varible from the closest scope. return null if not present. checks also from global scope.
     * @param name
     * @return
     */
    private Variable getClosestVariableByName(String name){
        ArrayList<Map<String, Variable>> reversedScopes = new ArrayList<>(scopesVariables);
        Collections.reverse(reversedScopes);
        for(Map<String,Variable> scope:reversedScopes){
            if(scope.containsKey(name)){
                return scope.get(name);
            }
        }
        return null;
    }

    public void validateIfAndWhileVariablesTypes(List<String> ifAndWhileParameters, int lineIndex) throws VerifierExceptions.InvalidIfWhileArguments{
        for (String var:ifAndWhileParameters){
            // checks that is boolean type

            if (RegexUtils.BOOLEAN_VALUE.matcher(var).matches()){
                return;
            }
            Variable tmpVar = getClosestVariableByName(var);
            if (tmpVar!=null && tmpVar.isInitiated()){
                if(booleanTypes.contains(tmpVar.getType()))
                    return;
            }

        }
        throw new VerifierExceptions.InvalidIfWhileArguments(ifAndWhileParameters.toString(),lineIndex);
        //check that var boolean or boolean type
    }

    public void validateMethodCallWithValidParams(Map.Entry<String, List<String>> functionNameAndParam, int lineIndex) throws BaseException{

        Method method = MethodValidator.getMethodByName(functionNameAndParam.getKey());
        if (method == null){
            //todo:
            throw new VerifierExceptions.VariableNotDefined(functionNameAndParam.getKey(),lineIndex );
        }
        List<String> variablesTypes = createVariablesValuesList(functionNameAndParam.getValue());
        method.validateValidCallForMethod(variablesTypes, lineIndex);
    }

    private List<String> createVariablesValuesList(List<String> params) {
        List<String> valueList = new ArrayList<>();
        for (String param:params){
            Variable tmpVar = getClosestVariableByName(param);
            if (tmpVar!=null && tmpVar.isInitiated()){
                valueList.add(tmpVar.getValue());
            }
            if (Utils.isValidType(param)){
                valueList.add(param);
            }
        }
        return valueList;
    }

    public void addFunctionParams(List<Variable> parameters) {
        for(Variable variable: parameters){
            addVariableToScope(variable.getName(),variable);
        }
    }
}

