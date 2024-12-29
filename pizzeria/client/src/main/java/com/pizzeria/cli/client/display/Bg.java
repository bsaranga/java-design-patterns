package com.pizzeria.cli.client.display;

public class Bg extends DisplayDecorator {

    private BgColor bgColor;

    public Bg(Display display) {
        super(display);
    }

    public Bg(Display display, BgColor bgColor) {
        super(display);
        this.bgColor = bgColor;
    }

    public Bg setBgColor(BgColor bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    @Override
    public void display(String message) {
        System.out.print(bgColor.getCode() + message + TextStyle.RESET.getCode());
    }

    @Override
    public String text(String message) {
        return bgColor.getCode() + message + TextStyle.RESET.getCode();
    }
    
}
