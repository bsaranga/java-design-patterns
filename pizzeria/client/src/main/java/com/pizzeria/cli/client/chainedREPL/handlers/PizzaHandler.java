package com.pizzeria.cli.client.chainedREPL.handlers;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.orderproc.Cart;
import com.pizzeria.cli.client.orderproc.OrderStatus;
import com.pizzeria.cli.client.state.order.AppStateProps;
import com.pizzeria.cli.client.state.resources.ResourceState;
import com.pizzeria.cli.client.strategies.Context;
import com.pizzeria.cli.client.strategies.StrategyFacade;

@Component
public class PizzaHandler extends Handler {
    
    @Autowired
	private Context strategyContext;

	@Autowired
	private StrategyFacade strategyFacade;

    @Autowired
    private Cart cart;

    @Autowired
    private OrderStatus orderStatus;

    @Autowired
    private ResourceState resourceState;

    @Override
    public void handleRequest(REPLRequest request) {
        if (request.getState() == AppStateProps.LOGGEDIN) {
            switch (request.getCommand().toLowerCase()) {
                case "1":
                    strategyContext.setStrategy(strategyFacade.getCuratedSelectionStrategy());
                    strategyContext.executeStrategy();
                    break;
                case "2":
                    break;
                default:
                    handleNext(request);
                    break;
            }
        } else if (request.getState() == AppStateProps.SELECTIONMODE && !Arrays.asList("b", "x").contains(request.getCommand())) {
            try {
                int selectedPizza = Integer.parseInt(request.getCommand());
                if (resourceState.getCuratedPizza(selectedPizza) != null) {
                    cart.addProduct(selectedPizza);
                    strategyContext.setStrategy(strategyFacade.getCuratedSelectionStrategy());
                    strategyContext.executeStrategy();
                    orderStatus.displayNumItemsInCart();
                } else {
                    throw new IllegalArgumentException("Selected pizza does not exist in the curated list.");
                }
            } catch (Exception e) {
                handleNext(request);
            }
        } else {
            handleNext(request);
        }
    }
    
}
