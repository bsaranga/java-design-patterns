package com.pizzeria.cli.client.display;

public class Color extends DisplayDecorator {

    private Colors color;

    public Color(IDisplay display, Colors color) {
        super(display);
        this.color = color;
    }

    @Override
    public void display(String message) {
        System.out.print(color.getCode() + message + TextStyle.RESET);
    }
    
}
