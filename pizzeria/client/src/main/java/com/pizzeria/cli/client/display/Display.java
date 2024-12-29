package com.pizzeria.cli.client.display;

public class Display implements IDisplay {
    
    @Override
    public void display(String message) {
        System.out.print(message);
    }

    @Override
    public String text(String message) {
        return message;
    }
}
