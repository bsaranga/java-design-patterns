package designpatterns;

// The builder pattern is a creational design pattern
// that lets you construct complex objects step by step.

public class builder {
    public static void main(String[] args) {
        
        // Using the builder pattern
        pizzaBuilder builder = new pizzaBuilder();

        builder.makeDough("Thin crust");
        builder.addSauce("Tomato");
        builder.addToppings("Mushrooms, olives, onions, and bell peppers");
        System.out.println(builder.getPizza());


        // Using the chained builder pattern
        chainedPizzaBuilder chainedBuilder = new chainedPizzaBuilder();

        Pizza pizza = chainedBuilder
            .makeDough("Sausage crust")
            .addSauce("Pesto")
            .addToppings("Pineapple, ham, and jalapenos")
            .getPizza();

        System.out.println(pizza);
    }
}