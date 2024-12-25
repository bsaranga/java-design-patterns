package designpatterns.state_pattern_pizza.state_objects;

import designpatterns.state_pattern_pizza.Order;
import designpatterns.state_pattern_pizza.OrderState;

public class DeliveredState implements OrderState {

    @Override
    public void getDetails(Order order) {
        System.out.println("Pizza is delivered");
    }

    @Override
    public void goToNextState(Order order) {
        System.out.println("There is no next state");
    }
}
