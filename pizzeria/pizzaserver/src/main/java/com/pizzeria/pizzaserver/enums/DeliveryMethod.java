package com.pizzeria.pizzaserver.enums;

public enum DeliveryMethod {

    STOREPICKUP("storepickup"),
    HOMEDELIVER("homedeliver");

    private String value;

    DeliveryMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
