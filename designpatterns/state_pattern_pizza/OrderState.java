package designpatterns.state_pattern_pizza;

public interface OrderState {
    public void getDetails(Order order);
    public void goToNextState(Order order);
}
