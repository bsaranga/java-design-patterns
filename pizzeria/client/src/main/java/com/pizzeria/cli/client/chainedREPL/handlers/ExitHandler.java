package com.pizzeria.cli.client.chainedREPL.handlers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.commands.Executor;
import com.pizzeria.cli.client.commands.ExitCommand;
import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.strategies.Context;
import com.pizzeria.cli.client.strategies.StrategyFacade;

@Component
public class ExitHandler extends Handler {

    @Autowired
	private Executor executor;
    
    @Autowired
	private Context strategyContext;

	@Autowired
	private StrategyFacade strategyFacade;

    @Override
    public void handleRequest(REPLRequest request) {
        if (Arrays.asList("", "3", "x").contains(request.getCommand())) {
            switch (request.getCommand().toLowerCase()) {
                case "" -> {

                }
                case "3", "x" -> {
                    if (!Arrays.asList(AppStateProps.NOOP, AppStateProps.REGISTERED, AppStateProps.LOGGEDOUT).contains(request.getState())) {
                        strategyContext.setStrategy(strategyFacade.getLogoutStrategy());
                        strategyContext.executeStrategy();
                    }
                    executor.setCommand(new ExitCommand()).execute();
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