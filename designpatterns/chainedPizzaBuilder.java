package designpatterns;

public class chainedPizzaBuilder {
    private Pizza pizza;
    
    // fields are usually initialized in the constructor
    public chainedPizzaBuilder() {
        this.pizza = new Pizza();
    }

    public chainedPizzaBuilder makeDough(String dough) {
        this.pizza.dough = dough;
        return this;
    }

    public chainedPizzaBuilder addSauce(String sauce) {
        this.pizza.sauce = sauce;
        return this;
    }

    public chainedPizzaBuilder addToppings(String toppings) {
        this.pizza.toppings = toppings;
        return this;
    }

    public Pizza getPizza() {
        return this.pizza;
    }
}
