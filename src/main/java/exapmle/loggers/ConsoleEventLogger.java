package exapmle.loggers;


import exapmle.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {
    @Autowired
    public ConsoleEventLogger(){};

    public void logEvent(Event message) {
        System.out.println(message.toString());
    }
}
