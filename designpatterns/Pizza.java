package designpatterns;

// pizza is a complex object,
// with multiple properties like dough, sauce, topping, etc.

public class Pizza {
    public String dough = "";
    public String sauce = "";
    public String toppings = "";

    @Override
    public String toString() {
        return "Pizza{" +
                "dough='" + dough + "'" +
                ", sauce='" + sauce + "'" +
                ", toppings='" + toppings + "'" +
                '}';
    }
}
