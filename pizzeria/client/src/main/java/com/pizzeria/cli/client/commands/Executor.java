package com.pizzeria.cli.client.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.state.order.AppState;

@Component
public class Executor {
    
    @Autowired
    private AppState state;

    private ICommand<AppStateProps> command;

    public Executor setCommand(ICommand<AppStateProps> command) {
        this.command = command;
        return this;
    }

    public void execute() {
        command.execute(state);
    }
}
