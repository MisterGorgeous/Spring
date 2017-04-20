package exapmle.loggers;

import exapmle.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {
    private File file;
    private String filename;
    private String encoding;


    //@Value("S:\git_rep\SpringCore\src\main\resources\result.txt")
    //@Value("${const.encoding}")
    @Autowired
    public FileEventLogger( String  filename,String encoding){
        this.filename = filename;
        this.encoding = encoding ;
    }

    @PostConstruct
    public void init() throws  IOException {
        this.file = new File(filename);
        if(!file.canWrite()) {
            throw new IOException();
        }
    }

    public void logEvent(Event message) {
        try {
            FileUtils.writeStringToFile(file,message.toString(),encoding,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
