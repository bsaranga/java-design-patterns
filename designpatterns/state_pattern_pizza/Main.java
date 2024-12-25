package designpatterns.state_pattern_pizza;

// Basically what the state pattern does is
// it changes object behavior according to state
public class Main {
    public static void main(String[] args) {
        // Pizza order states
        // 1. Order placed
        // 2. Preparing
        // 3. Baking
        // 4. Out for delivery
        // 5. Delivered

        Order pizzaOrder = new Order();
        pizzaOrder.goToNextState();
        pizzaOrder.goToNextState();
        pizzaOrder.goToNextState();
        pizzaOrder.goToNextState();
        pizzaOrder.goToNextState();
        pizzaOrder.getDetails();
    }
}
