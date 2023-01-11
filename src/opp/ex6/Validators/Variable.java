package opp.ex6.Validators;

import opp.ex6.Types;

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

    public boolean isFinal(){
        return this.isFinal;
    }
    public String getType(){
        return this.type;
    }


    public boolean isInitiated(){
        return this.isInitiated;
    }


}
