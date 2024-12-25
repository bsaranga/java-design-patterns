package designpatterns.state_pattern_pizza.state_objects;
import designpatterns.state_pattern_pizza.OrderState;
import designpatterns.state_pattern_pizza.Order;

public class PreparingState implements OrderState {

    @Override
    public void getDetails(Order order) {
        System.out.println("Order is being prepared");
    }

    @Override
    public void goToNextState(Order order) {
        order.setState(new BakingState());
    }
    
}
