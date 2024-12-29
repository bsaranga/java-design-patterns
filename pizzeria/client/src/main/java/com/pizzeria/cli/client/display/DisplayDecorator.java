package com.pizzeria.cli.client.display;

public abstract class DisplayDecorator implements IDisplay {
    protected IDisplay display;

    public DisplayDecorator(IDisplay display) {
        this.display = display;
    }

    @Override
    public void display(String message) {
        display.display(message);
    }
}
