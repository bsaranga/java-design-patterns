package com.pizzeria.cli.client.chainedREPL.handlers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.strategies.Context;
import com.pizzeria.cli.client.strategies.StrategyFacade;

@Component
public class EntryHandler extends Handler {

	@Autowired
	private Context strategyContext;

	@Autowired
	private StrategyFacade strategyFacade;

    @Override
    public void handleRequest(REPLRequest request) {
        if (Arrays.asList(AppStateProps.NOOP, AppStateProps.REGISTERED).contains(request.getState())) {
            switch (request.getCommand().toLowerCase()) {
                case "1":
                    strategyContext.setStrategy(strategyFacade.getAccountCreationStrategy());
                    strategyContext.executeStrategy();
                    break;
                case "2":
                    strategyContext.setStrategy(strategyFacade.getLoginStrategy());
                    strategyContext.executeStrategy();
                    break;
                default:
                    handleNext(request);
                    break;
            }
        } else {
            handleNext(request);
        }
    }
}
