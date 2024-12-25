package designpatterns.state_pattern_pizza.state_objects;
import designpatterns.state_pattern_pizza.OrderState;
import designpatterns.state_pattern_pizza.Order;

public class OrderPlacedState implements OrderState {

    @Override
    public void getDetails(Order order) {
        System.out.println("Order is placed");
    }

    @Override
    public void goToNextState(Order order) {
        order.setState(new PreparingState());
    }
    
}
