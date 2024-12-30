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
import com.pizzeria.cli.client.orderproc.DeliveryMethod;
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
                    while (true) {
                        boldDisplay.display("Select delivery method:\n");
                        colorDisplay.setColor(Color.WHITE).display("    1. Store Pickup\n");
                        colorDisplay.setColor(Color.WHITE).display("    2. Home Delivery\n");
                        var deliveryMethod = console.readLine(bgDisplay.setBgColor(BgColor.YELLOW).text(prompt)).trim();

                        if (deliveryMethod.equals("1")) {
                            orderStatus.setDeliveryMethod(DeliveryMethod.STOREPICKUP);
                            break;
                        } else if (deliveryMethod.equals("2")) {
                            orderStatus.setDeliveryMethod(DeliveryMethod.HOMEDELIVER);
                            break;
                        } else {
                            bgDisplay.setBgColor(BgColor.RED).display("Invalid delivery method");
                            System.out.println("\n");
                        }   
                    }

                    // select payment method
                    while (true) {
                        boldDisplay.display("Select payment method:\n");
                        colorDisplay.setColor(Color.WHITE).display("    1. Credit/Debit Card\n");
                        colorDisplay.setColor(Color.WHITE).display("    2. Apple Pay\n");
                        colorDisplay.setColor(Color.WHITE).display("    3. PayPal\n");
                        var paymentMethod = console.readLine(bgDisplay.setBgColor(BgColor.YELLOW).text(prompt)).trim();
                        if (paymentMethod.equals("1")) {
                            // payment method selected
                            break;
                        } else if (paymentMethod.equals("2")) {
                            // payment method selected
                            break;
                        } else if (paymentMethod.equals("3")) {
                            // payment method selected
                            break;
                        } else {
                            bgDisplay.setBgColor(BgColor.RED).display("Invalid payment method");
                            System.out.println("\n");
                        }
                    }

                    // send request to payment processor
                    colorDisplay.setColor(Color.GREEN).display("Processing payment...\n\n");
                    try {
                        Thread.sleep(3000); // Simulate a wait for payment processing
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Payment processing interrupted", e);
                    }
                    colorDisplay.setColor(Color.GREEN).display("Payment successful!\n\n");

                    // send order to kitchen

                    // change state
                    state.setState(AppStateProps.ORDERED);
                    state.setPrompt("Press 'b' to return to main menu...");

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
