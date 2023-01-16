package oop.ex6.main.utils;

import oop.ex6.main.exception.VerifierExceptions;

import java.util.ArrayList;
import java.util.List;

import static oop.ex6.main.utils.Utils.findSubStringOfBrackets;

/**
 * Utils for If and While blocks.
 *
 * @author itayyamin zurbb
 */
public class IfAndWhileUtils {

    /**
     *
     * @param line the current line string (which is candidate to be if and while block)
     * @param lineIndex the index of the line
     * @return
     * @throws VerifierExceptions.InvalidIfWhileArguments in case of invalid params
     */
    public static List<String> getIfOrWhileArgs(String line, int lineIndex) throws VerifierExceptions.InvalidIfWhileArguments {
        List<String> arguments = new ArrayList<>();
        String argumentsBeforeSplit = findSubStringOfBrackets(line);
        if (argumentsBeforeSplit.equals("")) {
            return arguments;
        } else {
            argumentsBeforeSplit = (argumentsBeforeSplit + "||").trim();
            if (RegexUtils.IF_WHILE_ARGUMENTS.matcher(argumentsBeforeSplit).matches()) {
                for (String value : argumentsBeforeSplit.split("(&&|\\|\\|)")) {
                    arguments.add(value.trim());
                }
            } else {
                throw new VerifierExceptions.InvalidIfWhileArguments(line, lineIndex);
            }
        }
        return arguments;
    }


}
