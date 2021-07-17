package com.fabrick.rs.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum FabrickTransactionTypeEnum {
    GBS_ACCOUNT_TRANSACTION_TYPE_0050("GBS_TRANSACTION_TYPE", "GBS_ACCOUNT_TRANSACTION_TYPE_0050"),
    GBS_ACCOUNT_TRANSACTION_TYPE_0034("GBS_TRANSACTION_TYPE", "GBS_ACCOUNT_TRANSACTION_TYPE_0034"),
    GBS_ACCOUNT_TRANSACTION_TYPE_0010("GBS_TRANSACTION_TYPE", "GBS_ACCOUNT_TRANSACTION_TYPE_0010"),
    NOT_SUPPORTED("NOT_SUPPORTED","NOT_SUPPORTED");

    @JsonProperty("enumeration")
    private String enumeration;
    @JsonProperty("value")
    private String value;


    FabrickTransactionTypeEnum(String enumeration, String value) {
        this.enumeration = enumeration;
        this.value = value;
    }
    @JsonCreator
    FabrickTransactionTypeEnum(String bhooo) {
        System.out.println(bhooo);
    }

    private static final Map<String, FabrickTransactionTypeEnum> lookup = new HashMap<>();

//    // Populate the lookup table on loading time
//    static {
//        for (FabrickTransactionTypeEnum type : FabrickTransactionTypeEnum.values()) {
//            lookup.put(type.enumeration, type);
//        }
//    }
//
//    public static FabrickTransactionTypeEnum get(String enumeration) {
//        return lookup.getOrDefault(enumeration, NOT_SUPPORTED);
//    }

//    @JsonCreator
//    public static FabrickTransactionTypeEnum forValues(@JsonProperty("enumeration") String enumeration, @JsonProperty("value") String value) {
//        return lookup.getOrDefault(enumeration, NOT_SUPPORTED);
//    }

    public String getEnumeration() {return enumeration;}
    public void setEnumeration(String enumeration) {this.enumeration = enumeration;}

    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}
}
