package com.pizzeria.cli.client.chainedREPL.handlers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.state.order.AppState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class StateHandler extends Handler {

    @Autowired
	private AppState state;

    @Override
    public void handleRequest(REPLRequest request) {
        if (Arrays.asList( "b").contains(request.getCommand())) {
            switch (request.getCommand().toLowerCase()) {
                case "b" -> {
                    if (Arrays.asList(AppStateProps.SELECTIONMODE).contains(request.getState())) {
                        state.setState(AppStateProps.LOGGEDIN);
                    }
                }
                default -> {
                    handleNext(request);   
                }
            }
        } else {
            handleNext(request);
        }
    }
    
}
