public class MicrosoftFactory implements UIComponentFactory {
    @Override public Button createButton()   { return new MicrosoftButton(); }
    @Override public Checkbox createCheckbox(){ return new MicrosoftCheckbox(); }
    @Override public Menu createMenu()       { return new MicrosoftMenu(); }
}