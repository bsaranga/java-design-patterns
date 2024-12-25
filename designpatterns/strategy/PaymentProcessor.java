package designpatterns.strategy;

public class PaymentProcessor {
    // holds a reference to an instance of the payment strategy
    private PaymentStrategy paymentStrategy;

    // we create the payment processor using an externally provided
    // payment strategy, either cash or credit card
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // we can set the payment strategy at runtime if we need
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // calls the pay method of the selected payment strategy
    public void processPayment(float amount) {
        paymentStrategy.pay(amount);
    }
}
