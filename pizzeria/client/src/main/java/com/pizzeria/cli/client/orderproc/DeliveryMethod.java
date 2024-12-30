package com.pizzeria.cli.client.orderproc;

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
