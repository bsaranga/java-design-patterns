package com.pizzeria.cli.client.strategies;

public class Context {
    
    private IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        if (strategy != null) {
            strategy.execute();
        }
        throw new IllegalStateException("Strategy not set");
    }
}
