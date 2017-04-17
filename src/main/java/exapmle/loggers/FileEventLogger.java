package exapmle.loggers;

import exapmle.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private File file;
    private String filename;
    private String encoding;

    public FileEventLogger(String filename,String encoding){
        this.filename = filename;
        this.encoding = encoding ;
    }

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
