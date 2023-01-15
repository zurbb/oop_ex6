package opp.ex6.Validators;

import java.util.regex.Pattern;

public class Const {
    public static final Pattern EMPTY_LINE_PATTERN = Pattern.compile("^\\s*$");

    public static final Pattern COMMENT_PATTERN = Pattern.compile("^//.*$");
    public static final Pattern ILEGAL_COMMENT = Pattern.compile("^[\\s]+//.*$");
    public final static Pattern STRUCTURE_PATTERN_FOR_COMPLETE_STATEMENT = Pattern.compile("([\\s]*[\\s]*)([a-zA-Z]+)([\\s]+[a-zA-Z]+)([\\s]*=[\\s]*[a-zA-Z]+[\\s]*)*([\\s]*;$)");//     final    string   name   =    eerfweg        ;
    public final static Pattern ASSIGNMENT_WITHOUT_TYPE = Pattern.compile("([\\s]*(final)*[\\s]*)([\\s]+[a-zA-Z]+)([\\s]*=[\\s]+[a-zA-Z]+[\\s]+)*([\\s]*;$)"); //          name      =    eerfweg        ;
    public final static Pattern isCanidateForVAribale = Pattern.compile("^[\\s]*(final )*[\\s]*(int|double|String|boolean|char)+[^\\(\\)\\{\\}\\[\\]]+;$"); //          name      =    eerfweg        ;
    public final static Pattern NAME = Pattern.compile("^(__|_[a-zA-Z0-9]|[a-zA-Z])([_a-zA-Z0-9])*$");


    public final static Pattern IF_WHILE_PATTERN = Pattern.compile("([\\s]*(if|while)[\\s]*\\(.*\\)\\{\\s*)");
    public final static Pattern IF_WHILE_ARGUMENTS = Pattern.compile("^(\\s*(((-|\\+)?\\d+|true|(__|_[a-zA-Z0-9]|[a-zA-Z])(\\w)*|false|(-|\\+)?\\d*\\.\\d+|\\d+\\.\\d*)[\\s]*(&&|\\|\\|)))+$");


    public final static Pattern VARIABLES_MUST_BE_WITH_EQUALS_SIGN = Pattern.compile("^([\\s]*[\\w]*[\\s]*=[\\s]*([\\\"']?[\\w]*[.]?[+|-]?[\\w]*)[\\\"']?[\\s]*,)*$");

    public final static Pattern METHOD_PATTERN_SIGNATURE = Pattern.compile("^([\\s]*void[\\s]+([a-zA-Z]{1}[\\w]*)[\\s]*\\((.*)\\)[\\s]*\\{[\\s]*$)");
    public final static Pattern METHOD_PATTERN_ARGUMENTS = Pattern.compile("([\\s]*(int|double|String|boolean|char)[\\s]+(__|_[a-zA-Z0-9]|[a-zA-Z])([_a-zA-Z0-9])*[\\s]*,)*");

    public static final Pattern START_WITH_TYPE =Pattern.compile("^(int |double |String |boolean |char )");


//    public final static Pattern TYPE = Pattern.compile("^(int|double|String|boolean|char)$");
    public final static Pattern TYPE = Pattern.compile("[\\s]*(int|double|String|boolean|char)");
    public final static Pattern BOOLEAN_VALUE = Pattern.compile("^(true|false|(\\+|-|)(\\d|.)+)$");
    public final static Pattern INT_VALUE = Pattern.compile("^([+|-]?\\d*)$");
    public final static Pattern DOUBLE_VALUE = Pattern.compile("^[+|\\-]?(\\d+\\.\\d*|\\d*\\.\\d+|\\d+)$");
    public final static Pattern STRING_VALUE = Pattern.compile("^(\"[\\x20-\\x7E]*\")$");
    public final static Pattern CHAR_VALUE = Pattern.compile("^'[\\x20-\\x7E]'$");
    public static final Pattern VARIABLES = Pattern.compile("^(([\\s]*[\\w]+[\\s]*|[\\s]*[\\w]+[\\s]*=[\\s]*[\\w]+[\\s]*),)*");
    public static final Pattern VARIABLE_ONLY_ONE_ASSIGMENT = Pattern.compile("^[\\s]*(_[\\w]+|[a-zA-z][\\w]*)[\\s]*=[\\s]*([\\w]*|.)+[\\s]*;$");


//    private final static Pattern STRUCTURE_PATTERN_FOR_COMPLETE_STATEMENT = Pattern.compile("/(\\s*)(int|double|boolean|char|String)(\\s*=\\s*)([\\x20-\\x2C\\x2E-\\x7E]+)");




}
