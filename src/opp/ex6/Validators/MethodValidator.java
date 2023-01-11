package opp.ex6.Validators;

import java.util.HashMap;

public class MethodValidator {
    static HashMap<String, Method> methods;

    static void addMethod(String name, Method method){
        methods.put(name, method);
    }
    // foo(a){
    //  a=b;
 /*       if(true){
            b=a;
    }*/
    // }

    static int isValid(String methodString){
        return 1;
//        Method method = new Method(methodString);
//        return method.isValid();
//        // varible type:string name :A = 2
//        method.add(tpye,a,2)
//        b =a;
//        if(method.caontiane(a))
//        ///check first line is in the format of "void +spaces + type + name + ( args)
//        // if there is while or if statements -->> find the closing and call IfAndWhileValidator
//        // check the last line in the format of "return;}"
    }
}
