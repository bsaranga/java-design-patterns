package com.pizzeria.cli.client.display;

public class Colored extends DisplayDecorator {

    private Color color;

    public Colored(Display display) {
        super(display);
    }

    public Colored(IDisplay display, Color color) {
        super(display);
        this.color = color;
    }

    public Colored setColor(Color color) {
        this.color = color;
        return this;
    }

    // mistake, add the super decorator
    @Override
    public void display(String message) {
        //super.display(color.getCode() + message + TextStyle.RESET.getCode());
        System.out.print(color.getCode() + message + TextStyle.RESET.getCode());
    }

    @Override
    public String text(String message) {
        return color.getCode() + message + TextStyle.RESET.getCode();
    }
    
}
