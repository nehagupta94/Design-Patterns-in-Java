package util;

/** ENUM for States */
public enum Luxury {
    BASIC("basic"), LUXURIOUS("moderatelyExpensive"), EXTRAVAGANT("superExpensive");

    private String value;

    Luxury(String nameIn){
        this.value = nameIn;
    }

    public String getValue(){
        return value;
    }
}

