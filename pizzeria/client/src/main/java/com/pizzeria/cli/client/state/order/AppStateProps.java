package com.pizzeria.cli.client.state.order;

public enum AppStateProps {
    // user states
    NOOP("noop"), // no operation
    REGISTERED("registered"),
    LOGGEDIN("loggedin"),
    LOGGEDOUT("loggedout"),

    // order states
    ADDED("add"),
    EMPTIED("remove"),
    CANCELLED("cancel"),
    ORDERED("order"),
    EXIT("exit"),

    // process states
    PREPARING("preparing"),
    BAKING("baking"),
    WRAPPING("wrapping"),
    READY("ready"),
    DELIVERING("delivering"),
    DELIVERED("delivered");

    private String value;

    AppStateProps(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}