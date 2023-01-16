package oop.ex6.main.exception;

/**
 * Class which defined all the exceptions for the Sjava verifier.
 *
 * @author itayyamin zurbb
 */
public class VerifierExceptions {

    public static class IllegalComment extends BaseException {
        public IllegalComment(String line, int lineIndex) {
            super("At line: " + lineIndex  + ". "
                    + " illegal comment: " + line);
        }
    }

    public static class ValueNotMatchToType extends BaseException {
        public ValueNotMatchToType(String type, String value, int lineIndex) {
            super("At line: " + lineIndex  + ". " + type + " cannot be assigned to value: " + value);
        }
    }

    public static class NotInPatternOfGlobalVariable extends BaseException {
        public NotInPatternOfGlobalVariable(String line, int lineIndex) {
            super("At line: " + lineIndex  + ". " + "Expected to get statement in the format of '(final) type name (= value);' but got: " + "'" + line + "'");
        }
    }

    public static class alreadyDefined extends BaseException {
        public alreadyDefined(String name, int lineIndex) {
            super("At line: " + lineIndex  + ". " + name + " ,already defined");
        }
    }

    public static class CannotModifieFinal extends BaseException {
        public CannotModifieFinal(String line, int lineIndex) {
            super("At line: " + lineIndex + ". " + "global variable is final ,cannot be modified at: " + line);
        }
    }

    public static class VariableNotDefined extends BaseException {
        public VariableNotDefined(String name, int lineIndex) {
            super("At line: " + lineIndex + ". " + "variable: " + name + "  not defined");
        }
    }

    public static class IllegalName extends BaseException {
        public IllegalName(String name, int lineIndex) {
            super("At line: " + lineIndex  + ". " + lineIndex + "unsupported name: " + name);
        }
    }

    public static class IllegalType extends BaseException {
        public IllegalType(String type, int lineIndex) {
            super("At line: " + lineIndex + ". " + "unsupported type: " + type);
        }
    }

    public static class MethodParamInvalid extends BaseException {
        public MethodParamInvalid(String params, int lineIndex) {
            super("At line: " + lineIndex  + ". " + "Invalid params: " + params);
        }
    }

    public static class InvalidMethodCall extends BaseException {
        public InvalidMethodCall(String methodCall, int lineIndex) {
            super("At line: " + lineIndex  + ". " + "Invalid Method call: " + methodCall);
        }
    }

    public static class InvalidMethodCloseBracket extends BaseException {
        public InvalidMethodCloseBracket(String line, int lineIndex) {
            super("At line: " + lineIndex  + ". " + "Invalid Bracket close: " + line);
        }
    }

    public static class MissingReturnStatement extends BaseException {
        public MissingReturnStatement(int line, int lineIndex) {
            super("At line: " + lineIndex  + ". " + "Missing return statement at: " + line);
        }
    }

    public static class InvalidIfWhileArguments extends BaseException {
        public InvalidIfWhileArguments(String line, int lineIndex) {
            super("At line: " + lineIndex + ". " + "Invalid param : " + line + " if/while arguments pattern.");
        }
    }

}
