package exapmle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {
    private String id;
    private String fullName;
    private String greeting;

    @Autowired
    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }


    public void setId(String id) {
        this.id = id;
    }


    @Value("Hello there!")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

}
