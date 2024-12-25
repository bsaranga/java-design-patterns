package designpatterns.strategy;

public class CreditCardStrategy implements PaymentStrategy {
    
    @Override
    public void pay(float amount) {
        float rate = 0.03f;
        float interest = amount * rate;
        System.out.println("Paying " + amount + ", and interest " + interest + " using credit card.");
    }
}
