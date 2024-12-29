package com.pizzeria.cli.client.display;

public class Bold extends DisplayDecorator {

    public Bold(IDisplay display) {
        super(display);
    }

    @Override
    public void display(String message) {
        System.out.print(TextStyle.BOLD.getCode() + message + TextStyle.RESET.getCode());
    }

    @Override
    public String text(String message) {
        return TextStyle.BOLD.getCode() + message + TextStyle.RESET.getCode();
    }
    
}
