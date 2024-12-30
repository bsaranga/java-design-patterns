package com.pizzeria.cli.client.chainedREPL;

import com.pizzeria.cli.client.state.order.AppStateProps;

public class REPLRequest {
    
    private String command;
    private AppStateProps state;

    public REPLRequest(String command, AppStateProps state) {
        this.command = command;
        this.state = state;
    }

    public String getCommand() {
        return command;
    }

    public AppStateProps getState() {
        return state;
    }
}
