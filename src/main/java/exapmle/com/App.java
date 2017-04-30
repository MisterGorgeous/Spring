package exapmle.com;

import exapmle.AppConfig;
import exapmle.com.aop.LoggingAspect;
import exapmle.com.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(AppConfig.class);
        ctx.refresh();



       /* App app = (App) ctx.getBean("app");
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");*/

        App app = ctx.getBean(App.class);
        Event event1 = ctx.getBean(Event.class);
        Event event2 = ctx.getBean(Event.class);

        LoggingAspect aspect = ctx.getBean(LoggingAspect.class);
        System.out.println(aspect.printStats());

        event1.setMsg("Info message for 1");
        event2.setMsg("Error message for 1");

        app.logEvent(EventType.INFO, event1);
        app.logEvent(EventType.ERROR, event2);
        app.logEvent(null, event2);

        System.out.println(aspect.printStats());
        // ctx.close();
    }
}
