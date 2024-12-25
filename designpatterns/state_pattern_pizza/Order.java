package designpatterns.state_pattern_pizza;

import designpatterns.state_pattern_pizza.state_objects.OrderPlacedState;

public class Order {
    private OrderState state;

    public Order() {
        this.state = new OrderPlacedState();
    }

    public void getDetails() {
        state.getDetails(this);
    }

    public void goToNextState() {
        state.goToNextState(this);
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
