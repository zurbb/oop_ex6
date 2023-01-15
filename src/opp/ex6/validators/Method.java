package opp.ex6.validators;

import opp.ex6.exception.BaseException;
import opp.ex6.exception.VerifierExceptions;
import opp.ex6.utils.IfAndWhileUtils;
import opp.ex6.utils.MethodUtils;
import opp.ex6.utils.RegexUtils;

import java.util.*;
//

public class Method {
     private static Map<String, Method> globalMethods= new HashMap<>();
     private List<Variable> parameters;

     private final int startPosInd ;
     private final int endPosInd ;

     public Method(int startPosInd,int endPosInd,String methodName, List<Variable> params){
          this.startPosInd= startPosInd;
          this.endPosInd = endPosInd;
          this.parameters = params;
          globalMethods.put(methodName,this);

     }

     public int getEndPosInd() {
          return endPosInd;
     }

     public int getStartPosInd() {
          return startPosInd;
     }

     public static boolean isNameAlreadyDefined(String methodName){
          return globalMethods.containsKey(methodName);
     }

     public List<Variable> getParameters(){
          return this.parameters;
     }

     public static void validateValidCallForMethod(String nameOfMethod, List<Variable> parameters) throws  VerifierExceptions.InvalidMethodCall{
          if(globalMethods.containsKey(nameOfMethod)){
               Method method = globalMethods.get(nameOfMethod);
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


}
