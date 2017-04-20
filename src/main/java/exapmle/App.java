package exapmle;

import exapmle.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class App {
    private Client client;
    private Map<EventType, EventLogger> loggers;


    @Autowired
    @Qualifier("fileEventLogger")
    // @Value("#{T(Event).isDay() ? FileEventLogger : ConsoleEventLogger}")
    private EventLogger deffaultEventLogger;


    @Autowired
    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event message) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = deffaultEventLogger;
        }

        logger.logEvent(message);
    }

    public static void main(String[] args) {

         //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        ctx.refresh();


        event1.setMsg("Info message for 1");
        event2.setMsg("Error message for 1");

        app.logEvent(EventType.INFO, event1);
        app.logEvent(EventType.ERROR, event2);
        //app.logEvent(null, event2);

        // ctx.close();
    }
}
