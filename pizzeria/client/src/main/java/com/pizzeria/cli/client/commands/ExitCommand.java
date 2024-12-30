package com.pizzeria.cli.client.commands;

import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.order.AppStateProps;

public class ExitCommand implements ICommand<AppStateProps> {
    
    @Override
    public void execute(IState<AppStateProps> state) {
        
        if (state.getState() != AppStateProps.ORDERED) {
            System.out.println("We hope you will order next time! Have a nice day.");
        } else {
            System.out.println("Thanks, and enjoy your pizza!");
        }

        state.setState(AppStateProps.EXIT);
    }    
}
