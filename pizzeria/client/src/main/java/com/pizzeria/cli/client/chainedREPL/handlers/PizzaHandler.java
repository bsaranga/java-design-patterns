package com.pizzeria.cli.client.chainedREPL.handlers;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.cli.client.chainedREPL.Handler;
import com.pizzeria.cli.client.chainedREPL.REPLRequest;
import com.pizzeria.cli.client.orderproc.Cart;
import com.pizzeria.cli.client.orderproc.OrderStatus;
import com.pizzeria.cli.client.orderproc.OrderType;
import com.pizzeria.cli.client.state.cache.CacheState;
import com.pizzeria.cli.client.state.order.AppStateProps;
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
    private CacheState cacheState;

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
        } else if (request.getCommand().toLowerCase().equals("c")) { // handle order confirmation
            if (cart.getProductIds().size() > 0) {
                strategyContext.setStrategy(strategyFacade.getCheckoutStrategy());
                strategyContext.executeStrategy();
            } else {
                handleNext(request);
            }
        } else if (request.getState() == AppStateProps.SELECTIONMODE && !Arrays.asList("b", "x").contains(request.getCommand())) {
            try {
                int selectedPizza = Integer.parseInt(request.getCommand()); // throws exception if not a number
                if (cacheState.getCuratedPizza(selectedPizza) != null) {
                    orderStatus.setOrderType(OrderType.COLLECTION);
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
        } else if (request.getState() == AppStateProps.ORDERED) {
            switch (request.getCommand().toLowerCase()) {
                case "r":
                    strategyContext.setStrategy(strategyFacade.getOrderStatusObserverStrategy());
                    strategyContext.executeStrategy();
                    break;
                default:
                    handleNext(request);
                    break;
            }
        } else if (request.getState() == AppStateProps.DELIVERED) {
            switch (request.getCommand().toLowerCase()) {
                case "f":
                    strategyContext.setStrategy(strategyFacade.getFeedbackStrategy());
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
