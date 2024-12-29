package com.pizzeria.cli.client.strategies;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.state.order.AppState;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Context {
    
    @Autowired
	private AppState state;
    private IStrategy<AppStateProps> strategy;

    public void setStrategy(IStrategy<AppStateProps> strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        if (strategy != null) {
            strategy.execute(state);
        } else throw new IllegalStateException("Strategy not set");
    }
}
