package exapmle;

import exapmle.aop.LoggingAspect;
import exapmle.aop.StatisticsAspect;
import exapmle.loggers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.text.DateFormat;
import java.util.*;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger("S:\\git_rep\\SpringCore\\src\\main\\resources\\result.txt","UTF8");
    }

    @Bean
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger(){
        return new CacheFileEventLogger("S:\\git_rep\\SpringCore\\src\\main\\resources\\result.txt","UTF8",2);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger(){
        List<EventLogger> loggers = new ArrayList<EventLogger>();
        loggers.add(fileEventLogger());
        loggers.add(consoleEventLogger());
        return new CombinedEventLogger(loggers);
    }

    @Bean
    public Client client(){
        return new Client("1","Jonh Smith");
    }

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateInstance();
    }

    @Bean
    public Date date(){
        return new Date();
    }

    @Bean
    public Event event(){
        return new Event(date(),dateFormat());
    }

    @Bean
    public App app(){
        Map<EventType,EventLogger> map = new HashMap<EventType, EventLogger>();
        map.put(EventType.ERROR,fileEventLogger());
        map.put(EventType.INFO,consoleEventLogger());
        return new App(client(),map);
    }

    @Bean
    public LoggingAspect loggingAspect(){
        return new LoggingAspect();
    }

}
