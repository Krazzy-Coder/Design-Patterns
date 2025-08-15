public class DecoratorPattern {
    public static void main(String[] args) {
        MargheritaPizza margheritaPizza = new MargheritaPizza();
        Cheese cheesePizza = new Cheese(margheritaPizza);
        Olives olivesCheesePizza = new Olives(cheesePizza);
        Jalapenos jalapenosOlivesCheesePizza = new Jalapenos(olivesCheesePizza);
        System.out.println("Description: " + jalapenosOlivesCheesePizza.getDescription());
        System.out.println("Cost: $" + jalapenosOlivesCheesePizza.getCost());
    }
}
