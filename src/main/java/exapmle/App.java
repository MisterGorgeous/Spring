package exapmle;


import exapmle.loggers.EventLogger;
import exapmle.loggers.FileEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private Map<EventType,EventLogger> loggers;
    private EventLogger loggerEvent;

    public App(Client client, EventLogger loggerEvent, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.loggerEvent = loggerEvent;
        this.loggers = loggers;
    }

    public void logEvent(EventType type,Event message){
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = loggerEvent;
        }

        logger.logEvent(message);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
      // ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        event1.setMsg("Info message for 1");
        event2.setMsg("Error message for 1");

        app.logEvent(EventType.INFO,event1);
        app.logEvent(EventType.ERROR,event2);

        ctx.close();
     }
}
