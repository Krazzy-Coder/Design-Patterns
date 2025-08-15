public class Jalapenos extends PizzaDecorator {

    public Jalapenos(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Jalapenos";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50; // Adding cost for Jalapenos
    }
}
