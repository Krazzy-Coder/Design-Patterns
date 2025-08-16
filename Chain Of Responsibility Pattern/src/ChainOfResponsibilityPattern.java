public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Logger logger = LoggerChain.getChainOfLoggers();
        logger.logMessage(Logger.INFO, "This is an information.");
        logger.logMessage(Logger.DEBUG, "This is a debug message.");
        logger.logMessage(Logger.ERROR, "This is an error message.");
    }
}
