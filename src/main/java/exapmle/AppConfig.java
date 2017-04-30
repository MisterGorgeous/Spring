package exapmle;

import exapmle.com.App;
import exapmle.com.Client;
import exapmle.com.Event;
import exapmle.com.EventType;
import exapmle.com.aop.LoggingAspect;
import exapmle.com.loggers.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.DateFormat;
import java.util.*;

@Configuration
//@ComponentScan(basePackages = "com")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        Map<Class<?>, Integer> map = new HashMap<Class<?>, Integer>();
        map.put(ConsoleEventLogger.class, 0);
        map.put(CacheFileEventLogger.class, 0);
        map.put(CombinedEventLogger.class, 0);
        return new LoggingAspect(map);
    }

    @Bean
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("C:\\Users\\Siarhei_Slabadniak\\IdeaProjects\\OtherTasks\\SpringPractise\\src\\main\\resources\\result.txt", "UTF8");
    }

    @Bean
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("C:\\Users\\Siarhei_Slabadniak\\IdeaProjects\\OtherTasks\\SpringPractise\\src\\main\\resources\\result.txt", "UTF8", 2);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger(List<EventLogger> loggers) {
        return new CombinedEventLogger(loggers);
    }

    @Bean
    public List<EventLogger> loggers(FileEventLogger fileEventLogger, ConsoleEventLogger consoleEventLogger) {
        List<EventLogger> loggers = new ArrayList<EventLogger>();
        loggers.add(fileEventLogger);
        loggers.add(consoleEventLogger);
        return loggers;
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateInstance();
    }

    @Bean
    public Date date() {
        return new Date();
    }

    @Bean
    @Scope("prototype")
    public Event event(Date date, DateFormat dateFormat) {
        return new Event(date, dateFormat);
    }

    @Bean
    public Map<EventType, EventLogger> map(FileEventLogger fileEventLogger, ConsoleEventLogger consoleEventLogger) {
        Map<EventType, EventLogger> map = new HashMap<EventType, EventLogger>();
        map.put(EventType.ERROR, fileEventLogger);
        map.put(EventType.INFO, consoleEventLogger);
        return map;
    }

    @Bean
    public App app(Client client, Map<EventType, EventLogger> map) {
        return new App(client, map);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(managerDataSource());
    }

    @Bean
    public DriverManagerDataSource managerDataSource() {
        return new DriverManagerDataSource("${connection_properties.url}", "${connection_properties.username}", "${connection_properties.password}");
    }

}
