package opp.ex6.validators;

public class Variable {
    boolean isFinal;
    String type;
    String name;
    String value;
    boolean isInitiated;

    Variable(boolean isFinal, String type, String name, String value) {
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

    public boolean isFinal(){
        return this.isFinal;
    }
    public String getType(){
        return this.type;
    }

    public void initiate(){
        this.isInitiated= true;
    }
    public boolean isInitiated(){
        return this.isInitiated;
    }


}
