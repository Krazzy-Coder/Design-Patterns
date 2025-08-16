public class AppleFactory implements UIComponentFactory {
    @Override public Button createButton()   { return new AppleButton(); }
    @Override public Checkbox createCheckbox(){ return new AppleCheckbox(); }
    @Override public Menu createMenu()       { return new AppleMenu(); }
}