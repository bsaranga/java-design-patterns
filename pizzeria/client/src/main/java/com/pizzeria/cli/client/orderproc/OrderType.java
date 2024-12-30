package com.pizzeria.cli.client.orderproc;

public enum OrderType {

    COLLECTION("collection"),
    CUSTOM("custom");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
