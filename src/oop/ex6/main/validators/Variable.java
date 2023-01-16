package oop.ex6.main.validators;

public class Variable {
    private boolean isFinal=false;
    private String type;
    private String name;
    private String value;
    private boolean isInitiated = false;

    public Variable(boolean isFinal, String type, String name, String value) {
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this.value = value;
        this.isInitiated =true;
    }

    Variable(boolean isFinal, String type, String name) {
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this.isInitiated =false;
    }

    public Variable(String type, String name, boolean isInitiated){
        this.type =type;
        this.name = name;
        this.isFinal =false;
        this.isInitiated = isInitiated;
    }

    public Variable(Variable other) {
        this.isFinal = other.isFinal();
        this.type = other.getType();
        this.name = other.getName();
        this.value = other.getValue();
        this.isInitiated = other.isInitiated();
    }

    public String getName(){
        return this.name;
    }

    public boolean isFinal(){
        return this.isFinal;
    }
    public String getType(){
        return this.type;
    }

    public String getValue(){
        return this.value;
    }

    public void initiate(){
        this.isInitiated= true;
    }
    public boolean isInitiated(){
        return this.isInitiated;
    }


}
