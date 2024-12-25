package designpatterns.strategy;

public class Main {
    public static void main(String[] args) {

        CashStrategy cashStrategy = new CashStrategy();
        CreditCardStrategy creditCardStrategy = new CreditCardStrategy();

        PaymentProcessor paymentProcessor = new PaymentProcessor(cashStrategy);
        
        paymentProcessor.processPayment(100.0f);

        paymentProcessor.setPaymentStrategy(creditCardStrategy);

        paymentProcessor.processPayment(100.0f);
    }
}
