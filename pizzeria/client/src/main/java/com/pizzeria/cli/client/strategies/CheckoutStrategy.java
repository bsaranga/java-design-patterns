package com.pizzeria.cli.client.strategies;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pizzeria.cli.client.display.Bg;
import com.pizzeria.cli.client.display.BgColor;
import com.pizzeria.cli.client.display.Bold;
import com.pizzeria.cli.client.display.Color;
import com.pizzeria.cli.client.display.Colored;
import com.pizzeria.cli.client.display.DisplayFacade;
import com.pizzeria.cli.client.orderproc.Cart;
import com.pizzeria.cli.client.orderproc.OrderStatus;
import com.pizzeria.cli.client.orderproc.OrderType;
import com.pizzeria.cli.client.state.IState;
import com.pizzeria.cli.client.state.cache.CacheState;
import com.pizzeria.cli.client.state.order.AppStateProps;

@Component
public class CheckoutStrategy implements IStrategy<AppStateProps> {

    @Value("${app.pizzaserver}")
	private String pizza_server_url;

    @Autowired
	private RestTemplate restTemplate;

    @Autowired
    private CacheState cacheState;

    @Autowired
    private OrderStatus orderStatus;

    @Autowired
    private Cart cart;

    Console console = System.console();
    Bg bgDisplay = DisplayFacade.getBgDisplay();
    Bold boldDisplay = DisplayFacade.getBoldDisplay();
    Colored colorDisplay = DisplayFacade.getColorDisplay();
    String prompt = "Enter command: ";

    @Override
    public void execute(IState<AppStateProps> state) {
        try {
            if (orderStatus.getOrderType() == OrderType.COLLECTION) {

                List<Integer> addedProducts = cart.getProductIds();

                colorDisplay.setColor(Color.GREEN).display("Please review your order:\n\n");

                double total = 0.0;

                for (var productId : addedProducts) {
                    var curatedPizza = cacheState.getCuratedPizza(productId.intValue());
                    boldDisplay.display(String.format("%s, $%.2f\n", curatedPizza.name(), curatedPizza.price()));
                    total += curatedPizza.price();
                }

                boldDisplay.display(String.format("Total: $%.2f\n", total));

                colorDisplay.setColor(Color.GREEN).display("\n\nWould you like to proceed with the order? (y/n): ");
                String command = console.readLine().trim();

                if (command.toLowerCase().equals("y")) {
                    
                    // select delivery method

                    // select payment method

                    // send request to payment processor

                    // payment complete

                    // send order to kitchen

                } else {
                    cart.clearCart();
                    state.setState(AppStateProps.CANCELLED);
                    state.setPrompt(prompt);
                    bgDisplay.setBgColor(BgColor.RED).display("Order cancelled, and cart cleared\n");
                }

            } else if (orderStatus.getOrderType() == OrderType.CUSTOM) {
                // ingredients based order processing
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during checkout", e);
        }
    }
    
}
