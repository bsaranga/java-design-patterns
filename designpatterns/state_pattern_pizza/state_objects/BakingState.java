package designpatterns.state_pattern_pizza.state_objects;

import designpatterns.state_pattern_pizza.Order;
import designpatterns.state_pattern_pizza.OrderState;

public class BakingState implements OrderState {

    @Override
    public void getDetails(Order order) {
        System.out.println("Pizza is being baked...");
    }

    @Override
    public void goToNextState(Order order) {
        order.setState(new OutForDeliveryState());
    }
    
}
