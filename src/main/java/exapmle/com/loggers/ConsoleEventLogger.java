package exapmle.com.loggers;


import exapmle.com.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {
    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Autowired
    public ConsoleEventLogger(){};

    public void logEvent(Event message) {
        System.out.println(message.toString());
        //jdbcTemplate.update("SELECT * From db(?,?)",message.toString());
    }

}
