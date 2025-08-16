public class DebugLogger extends Logger{
    public DebugLogger() {
        this.level = DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debug Logger: " + message);
    }
}
