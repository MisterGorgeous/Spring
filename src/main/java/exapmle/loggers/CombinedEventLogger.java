package exapmle.loggers;

import exapmle.Event;

import java.util.List;


public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> events;

    public CombinedEventLogger(List<EventLogger> events){
        this.events = events;
    }

    public void logEvent(Event message) {
        for(EventLogger logger:events){
            logger.logEvent(message);
        }
    }
}
