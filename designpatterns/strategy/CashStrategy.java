package designpatterns.strategy;

public class CashStrategy implements PaymentStrategy {
    
    private String currency;

    public CashStrategy() {
        this.currency = "USD";
    }

    public CashStrategy(String currency) {
        this.currency = currency;
    }

    @Override
    public void pay(float amount) {
        if (currency.equals("USD"))
            System.out.println("Paying " + "$" + amount + " using cash.");
        else if (currency.equals("Rupees"))
            System.out.println("Paying " + "₹" + amount + " using cash.");
        else
        System.out.println("Paying " + amount + " using cash.");
    }
}
