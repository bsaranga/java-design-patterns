package com.pizzeria.cli.client.display;

public enum TextStyle {
    RESET("\033[0m"),  // Reset
    BOLD("\033[1m");   // Bold

    private final String code;

    TextStyle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
