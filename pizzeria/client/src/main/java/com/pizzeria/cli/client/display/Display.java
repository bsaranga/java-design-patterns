package com.pizzeria.cli.client.display;

public class Display implements IDisplay {
    
    @Override
    public void display(String message) {
        System.out.println(message);
    }
}
