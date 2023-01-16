package oop.ex6.main.utils;

import oop.ex6.main.validators.MethodValidator;
import oop.ex6.main.validators.Variable;
import oop.ex6.main.exception.BaseException;
import oop.ex6.main.exception.VerifierExceptions;

import java.util.*;

import static oop.ex6.main.utils.Utils.findSubStringOfBrackets;

/**
 * Utils for Methods.
 */
public class MethodUtils {

    /**
     * @param methodLine the line number
     * @return true if it is matching to method statement
     */
    public static boolean validateMethodSignature(String methodLine) {
        return RegexUtils.METHOD_PATTERN_SIGNATURE.matcher(methodLine).find();
    }

    /**
     * @param methodLine the line we read
     * @param lineIndex  the line number
     * @return the method name
     * @throws VerifierExceptions.alreadyDefined
     */
    public static String getMethodName(String methodLine, int lineIndex) throws VerifierExceptions.alreadyDefined {
        // first validate name and if false throw VerifierExceptions.IllegalName
        methodLine = methodLine.trim();
        int openBracketLine = methodLine.indexOf('(');
        String name = methodLine.substring(0, openBracketLine).split("\\s")[1];
        if (MethodValidator.isNameAlreadyDefined(name)) {
            throw new VerifierExceptions.alreadyDefined(name, lineIndex);
        }
        return name;
    }

    /**
     *
     * @param methodLine the line we are currently reading
     * @param lineIndex the index of the line
     * @return the methods params
     * @throws VerifierExceptions.MethodParamInvalid in case of invalid params
     */
    public static List<Variable>
    getMethodParams(String methodLine, int lineIndex) throws VerifierExceptions.MethodParamInvalid {

        List<Variable> parameters = new ArrayList<>();
        String methodArguments = findSubStringOfBrackets(methodLine);
        if (methodArguments.trim().isEmpty()) {
            return parameters;
        }
        if (!RegexUtils.METHOD_PATTERN_ARGUMENTS.matcher(methodArguments + ",").matches()) {
            throw new VerifierExceptions.MethodParamInvalid(methodArguments, lineIndex);
        }
        HashSet<String> functionParamNames = new HashSet<>();

        String[] vars = methodArguments.split(",");
        for (String typeAndName : vars) {
            typeAndName = typeAndName.trim();
            String[] typeAndNameSplit = typeAndName.split("\\s+");
            String type = typeAndNameSplit[0].trim(), name = typeAndNameSplit[1].trim();

            if (functionParamNames.contains(name)) {
                throw new VerifierExceptions.MethodParamInvalid(methodArguments, lineIndex);
            }
            functionParamNames.add(name);

            parameters.add(new Variable(type, name, true));
        }
        return parameters;
    }


    /**
     *
     * @param line
     * @return
     */
    public static Map.Entry<String, List<String>> functionCallArgumentsValidation(String line) {
        line = line.trim();
        int openBracketIndex = line.indexOf('(');
        String name = line.substring(0, openBracketIndex);
        List<String> arguments = new ArrayList<>();
        String argumentsBeforeSplit = findSubStringOfBrackets(line);
        if (argumentsBeforeSplit.equals("")) {
            return Map.entry(name, arguments);
        } else {
            for (String value : argumentsBeforeSplit.split(",")) {
                arguments.add(value.trim());
            }
            for (String argument : arguments) {
                checkValidArgumentPattern(argument);
            }
        }
        return Map.entry(name, arguments);
    }
    //foo(x)

    private static void checkValidArgumentPattern(String argument) {
        if (RegexUtils.BOOLEAN_VALUE.matcher(argument).matches()) {
            return;
        }
        if (RegexUtils.INT_VALUE.matcher(argument).matches()) {
            return;
        }
        if (RegexUtils.DOUBLE_VALUE.matcher(argument).matches()) {
            return;
        }
        if (RegexUtils.STRING_VALUE.matcher(argument).matches()) {
            return;
        }
        if (RegexUtils.CHAR_VALUE.matcher(argument).matches()) {
            return;
        }
        if (RegexUtils.VARIABLES_NAME.matcher(argument).matches()) {
            return;
        }
        //todo: change exception
        throw new RuntimeException();
    }

    public static Map.Entry<Integer, Integer> forwardScanner(Scanner scanner, int start) throws BaseException {
        int brackets = 1;
        int endLine = start;
        String line = "";
        String prevLine = "";

        while (scanner.hasNextLine() && brackets > 0) {
            endLine++;
            prevLine = line;
            line = scanner.nextLine();
            if (RegexUtils.IF_WHILE_PATTERN.matcher(line).matches()) {
                brackets++;
            }
            if (line.trim().equals("}")) {
                brackets--;
            }
        }
        line = line.trim();
        if (brackets != 0 || !line.equals("}")) {
            throw new VerifierExceptions.InvalidMethodCloseBracket(line, endLine);
        }
        if (!prevLine.trim().equals("return;")) {
            throw new VerifierExceptions.MissingReturnStatement(endLine, endLine);
        }

        return Map.entry(start, endLine);
    }


}
