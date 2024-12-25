package designpatterns.strategy;

public class CashStrategy implements PaymentStrategy {
    
    @Override
    public void pay(float amount) {
        System.out.println("Paying " + amount + " using cash.");
    }
}
