package com.pizzeria.cli.client.prompters;

import com.pizzeria.cli.client.display.IDisplay;
import com.pizzeria.cli.client.state.order.AppStateProps;

public class Prompter {
    
    private IDisplay display;
    
    public Prompter(IDisplay display) {
        this.display = display;
    }

    public void DisplayPromptForState(AppStateProps stateProp) {
        switch (stateProp) {
            case NOOP:
                display.display("Available Commands:\n");
                display.display("    1. Create Account\n");
                display.display("    2. Log In\n");
                display.display("    3. Exit\n\n");
                break;
            case REGISTERED:
                display.display("You may log in now...\n");
                display.display("Available Commands:\n");
                display.display("    1. Create Account\n");
                display.display("    2. Log In\n");
                display.display("    3. Exit\n\n");
                break;
            case LOGGEDIN:
                display.display("\nAvailable Options:\n");
                display.display("    1. See our top 5 classic collection\n");
                display.display("    2. Build Your Own Pizza\n");
                display.display("    3. Logout & Exit\n\n");
                break;
            case SELECTIONMODE:
                display.display("Available Commands:\n");
                display.display("    b. Go back\n");
                display.display("    x. Logout & Exit\n\n");
                break;
            case ADDED:
                display.display("You may add more items or order...\n");
                break;
            case EMPTIED:
                display.display("You may add items or order...\n");
                break;
            case CANCELLED:
                display.display("You may order again...\n");
                break;
            case ORDERED:
                display.display("You may track your order...\n");
                break;
            case EXIT:
                display.display("You may exit the application...\n");
                break;
            case PREPARING:
                display.display("Your order is being prepared...\n");
                break;
            case BAKING:
                display.display("Your order is being baked...\n");
                break;
            case WRAPPING:
                display.display("Your order is being wrapped...\n");
                break;
            case READY:
                display.display("Your order is ready for delivery...\n");
                break;
            case DELIVERING:
                display.display("Your order is being delivered...\n");
                break;
            case DELIVERED:
                display.display("Your order has been delivered...\n");
                break;
            default:
                display.display("Invalid state...\n");
                break;
        }
    }
}
