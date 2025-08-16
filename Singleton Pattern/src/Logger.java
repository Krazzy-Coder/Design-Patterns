public class Logger {
    private static Logger instance;

    private Logger() { }  // private constructor

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();   // race condition risk!
        }
        return instance;
    }

    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
