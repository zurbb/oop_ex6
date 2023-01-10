package opp.ex6;
public enum Types {
    INT("int"), DOUBLE("double"), BOOLEAN("boolean"), CHAR("char"), STRING("string");

    private final String type;

    Types(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}