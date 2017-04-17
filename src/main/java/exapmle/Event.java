package exapmle;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private static Random random;
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

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

    @Override
    public String toString() {
        return "Id:" + id + ", Date:" + df.format(date) + "Msg:" + msg;
    }
}
