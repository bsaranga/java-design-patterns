package com.pizzeria.cli.client.display;

public class DisplayFacade {
    private static Display display = new Display();
    private static Bold boldDisplay = new Bold(display);
    private static Colored colorDisplay = new Colored(display);
    private static Bg bgDisplay = new Bg(display);

    public static Display getDisplay() {
        return display;
    }

    public static Bold getBoldDisplay() {
        return boldDisplay;
    }

    public static Colored getColorDisplay() {
        return colorDisplay;
    }

    public static Bg getBgDisplay() {
        return bgDisplay;
    }
}
