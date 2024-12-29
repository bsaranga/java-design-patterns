package com.pizzeria.cli.client.display;

public class Display {
    // ANSI escape codes
    private static final String RESET = "\033[0m";  // Reset
    private static final String BOLD = "\033[1m";   // Bold

    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String MAGENTA = "\033[35m";
    private static final String CYAN = "\033[36m";
    private static final String WHITE = "\033[37m";

    public static void bold(String message) {
        System.out.print(BOLD + message + RESET);
    }
}
