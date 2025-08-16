public class UIFactoryProvider {
    public static UIComponentFactory getFactory(Vendor vendor) {
        if (vendor == null) throw new IllegalArgumentException("Vendor cannot be null");
        switch (vendor) {
            case APPLE:      return new AppleFactory();
            case MICROSOFT:  return new MicrosoftFactory();
            default: throw new IllegalArgumentException("Unknown vendor: " + vendor);
        }
    }
}
