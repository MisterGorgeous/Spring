package exapmle.loggers;


import exapmle.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event message) {
        System.out.println(message.toString());
    }
}
