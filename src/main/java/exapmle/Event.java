package exapmle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

@Component
@Scope("prototype")
public class Event {
    private static Random random;
    private int id;
    private String msg;
    private static Date date;
    private DateFormat df;

    @Autowired
    public Event(Date date, DateFormat df) {
        if(random == null){
            random = new Random();
        }
        this.date = date;
        this.df = df;
        this.id = random.nextInt();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static boolean  isDay(){
        return date.getTime() <= 16;
    }

    @Override
    public String toString() {
        return "Id:" + id + ", Date:" + df.format(date) + "Msg:" + msg;
    }
}
