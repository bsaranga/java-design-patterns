package designpatterns;

public class pizzaBuilder {
    
    private Pizza pizza;
    
    // fields are usually initialized in the constructor
    public pizzaBuilder() {
        this.pizza = new Pizza();
    }

    public void makeDough(String dough) {
        this.pizza.dough = dough;
    }

    public void addSauce(String sauce) {
        this.pizza.sauce = sauce;
    }

    public void addToppings(String toppings) {
        this.pizza.toppings = toppings;
    }

    public Pizza getPizza() {
        return this.pizza;
    }
}
