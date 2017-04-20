package exapmle.loggers;

import exapmle.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> events;

    @Autowired
    public CombinedEventLogger(List<EventLogger> events){
        this.events = events;
    }

    public void logEvent(Event message) {
        for(EventLogger logger:events){
            logger.logEvent(message);
        }
    }
}
