package opp.ex6.exception;

public class VerifierExceptions {
    public static class FinalNotInitiated extends BaseException
    {
//        private static final long serialVersionUID = 3555714415375055302L;
        public FinalNotInitiated(String msg) {
            super(msg);
        }
    }

    public static class IllegalGlobalStatement extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public IllegalGlobalStatement(String line) {
            super("illegal global statement :"+line);
        }
    }

    public static class IllegalComment extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public IllegalComment(String line) {
            super("illegal comment: "+line);
        }
    }

    public static class ValueNotMatchToType extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public ValueNotMatchToType(String type, String value) {
            super(type +" cannot be assigned to value: " + value);
        }
    }

    public static class NotInPatternOfGlobalVariable extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public NotInPatternOfGlobalVariable(String line) {
            super("Expected to get statement in the format of '(final) type name (= value);' but got: " +"'" +line+"'");
        }
    }

    public static class alreadyDefined extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public alreadyDefined(String name) {
            super(name+" ,already defined");
        }
    }

    public static class GlobalVariableNotDefined extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public GlobalVariableNotDefined(String name) {
            super("global variable: "+name+" ,not defined");
        }
    }

    public static class CannotModifieFinal extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public CannotModifieFinal(String line) {
            super("global variable is final ,cannot be modified at: " +line);
        }
    }

    public static class VariableNotDefined extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public VariableNotDefined(String name) {
            super("variable: "+name+"  not defined");
        }
    }
    public static class IllegalName extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public IllegalName(String name) {
            super("unsupported name: "+name);
        }
    }

    public static class IllegalType extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public IllegalType(String type) {
            super("unsupported type: "+type);
        }
    }

    public static class MethodParamInvalid extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public MethodParamInvalid(String params) {
            super("Invalid params: "+params);
        }
    }

    public static class InvalidMethodCall extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public InvalidMethodCall(String methodCall) {
            super("Invalid Method call: "+methodCall);
        }
    }

    public static class InvalidMethodCloseBracket extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public InvalidMethodCloseBracket(String line, int lineIndex) {
            super("Invalid Bracket close: "+ line+", at line:"+lineIndex);
        }
    }

    public static class MissingReturnStatement extends BaseException
    {
        //        private static final long serialVersionUID = 3555714415375055302L;
        public MissingReturnStatement(int line) {
            super("Missing return statement at: "+line);
        }
    }
    public static class InvalidIfWhileArguments extends BaseException{
        public InvalidIfWhileArguments(String line, int lineIndex){
            super("Invalid param : "+line+ " if/while arguments pattern in line: "+lineIndex);
        }
    }

}
