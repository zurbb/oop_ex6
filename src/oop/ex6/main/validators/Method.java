package oop.ex6.main.validators;

import oop.ex6.main.utils.IfAndWhileUtils;
import oop.ex6.main.utils.MethodUtils;
import oop.ex6.main.utils.RegexUtils;
import oop.ex6.main.utils.Utils;
import oop.ex6.main.exception.BaseException;
import oop.ex6.main.exception.VerifierExceptions;

import java.util.*;
//

public class Method {
     private List<Variable> parameters;

     private final int startPosInd ;
     private final int endPosInd ;
     private final String name;

     public Method(int startPosInd,int endPosInd,String methodName, List<Variable> params){
          this.startPosInd= startPosInd;
          this.endPosInd = endPosInd;
          this.parameters = params;
          this.name = methodName;
     }

     public static void addMethod(int startPosInLine, int endPosInLine, String methodName, List<Variable> params) {
          MethodValidator.getGlobalMethods().put(methodName,  new Method(startPosInLine,endPosInLine,methodName,params));
     }

     public int getEndPosInd() {
          return endPosInd;
     }

     public int getStartPosInd() {
          return startPosInd;
     }


     public List<Variable> getParameters(){
          return this.parameters;
     }

     public void validateValidCallForMethod(List<String> parametersValues, int lineIndex) throws  VerifierExceptions.InvalidMethodCall{

        if(getParameters().size()==parametersValues.size()){
             for(int i=0; i< getParameters().size();i++){
                  if(!Utils.isTypesMatchValue(getParameters().get(i).getType(),parametersValues.get(i))){
                      throw new VerifierExceptions.InvalidMethodCall(parametersValues.toString(),lineIndex);
                  }
             }
             return;
        }
        throw new VerifierExceptions.InvalidMethodCall(parametersValues.toString(),lineIndex);


     }

     public void validate(List<String> lines, ScopeVariablesValidator scopeVariablesValidator) throws BaseException {
         scopeVariablesValidator.openScope();
         scopeVariablesValidator.addFunctionParams(parameters);
          int lineIndex = getStartPosInd()-1;
          for(String line: lines){

              lineIndex+=1;
               line = line.trim();
               if(RegexUtils.EMPTY_LINE_PATTERN.matcher(line).matches()|| RegexUtils.COMMENT_PATTERN.matcher(line).matches()){
                    continue;
               }
               if(RegexUtils.ILEGAL_COMMENT.matcher(line).matches()){
                    throw new VerifierExceptions.IllegalComment(line, lineIndex);
               }
               if(line.trim().equals("}")){
                    scopeVariablesValidator.removeScope();
                    continue;
               }
               if(line.trim().equals("return;")){
                   continue;
              }
             if(RegexUtils.IF_WHILE_PATTERN.matcher(line).matches()){
                    scopeVariablesValidator.openScope();
                    List<String> ifAndWhileParameters = IfAndWhileUtils.getIfOrWhileArgs(line,lineIndex);
                    scopeVariablesValidator.validateIfAndWhileVariablesTypes(ifAndWhileParameters, lineIndex);
                    continue;
               }
               if(RegexUtils.METHOD_CALL_PATTERN_SIGNATURE.matcher(line).matches()){
                    Map.Entry<String,List<String>> functionNameAndParam = MethodUtils.functionCallArgumentsValidation(line);

                    scopeVariablesValidator.validateMethodCallWithValidParams(functionNameAndParam, lineIndex);
                    continue;
               }
              scopeVariablesValidator.validate(line, lineIndex);

          }
     }


}
