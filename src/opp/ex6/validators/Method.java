package opp.ex6.validators;

import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.utils.IfAndWhileUtils;
import opp.ex6.utils.MethodUtils;
import opp.ex6.utils.RegexUtils;

import java.util.*;
//

public class Method {
     private List<Variable> parameters;

     private final int startPosInd ;
     private final int endPosInd ;

     public Method(int startPosInd,int endPosInd,String methodName, List<Variable> params){
          this.startPosInd= startPosInd;
          this.endPosInd = endPosInd;
          this.parameters = params;
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

     public static void validateValidCallForMethod(String nameOfMethod, List<Variable> parameters) throws  VerifierExceptions.InvalidMethodCall{
          if(MethodValidator.getGlobalMethods().containsKey(nameOfMethod)){
               Method method = MethodValidator.getGlobalMethods().get(nameOfMethod);
               List<Variable> methodParams = method.getParameters();
               if(parameters.size() == methodParams.size()){
                    for(int i =0; i<parameters.size();i++){
                         if(!parameters.get(i).getType().equals(methodParams.get(i).getType())){
                              throw new VerifierExceptions.InvalidMethodCall(nameOfMethod);
                         }
                    }
                    return;
               }
          }
          throw new VerifierExceptions.InvalidMethodCall(nameOfMethod);

     }

     public void validate(List<String> lines) throws BaseException {
          for(String line: lines){
               line = line.trim();
               if(RegexUtils.EMPTY_LINE_PATTERN.matcher(line).matches()|| RegexUtils.COMMENT_PATTERN.matcher(line).matches()){
                    continue;
               }
               if(RegexUtils.ILEGAL_COMMENT.matcher(line).matches()){
                    throw new VerifierExceptions.IllegalComment(line);
               }
               if(true){
                    int x = 34;
               }
               //TODO:yamin
               if(RegexUtils.IF_WHILE_PATTERN.matcher(line).matches()){
                    List<String> ifAndWhileParameters = IfAndWhileUtils.ifWhileArgumentsValidation(line);

               }
               if(RegexUtils.METHOD_CALL_PATTERN_SIGNATURE.matcher(line).matches()){
                    //todo change
                    int t = 1;
//                    Map.entry<String,List<String>> functionNameAndParam = MethodUtils.functionCallArgumentsValidation(line);
               }

          }
     }


}
