package com.pizzeria.cli.client.chainedREPL;

public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(REPLRequest request);

    public void handleNext(REPLRequest request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
