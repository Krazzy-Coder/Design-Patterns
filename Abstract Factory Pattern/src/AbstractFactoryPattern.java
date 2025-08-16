public class AbstractFactoryPattern {
    public static void main(String[] args) {
        UIComponentFactory factory = UIFactoryProvider.getFactory(Vendor.APPLE);
        renderScreen(factory);

        factory = UIFactoryProvider.getFactory(Vendor.MICROSOFT);
        renderScreen(factory);
    }

    private static void renderScreen(UIComponentFactory factory) {
        Button b = factory.createButton();
        Checkbox c = factory.createCheckbox();
        Menu m = factory.createMenu();

        b.render();
        c.render();
        m.render();
        System.out.println("---");
    }
}
