public class InfoLogger extends Logger{
    public InfoLogger() {
        this.level = INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("Info Logger: " + message);
    }
}
