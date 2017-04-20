package exapmle.loggers;

import exapmle.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {
    private List<Event> cache;
    private int cacheSize;

    @Autowired
    public CacheFileEventLogger(String filename,String encoding ,int cacheSize){
        super(filename,encoding);
        this.cache = new ArrayList<Event>(cacheSize);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event message) {
        cache.add(message);

        if(cache.size() >= cacheSize) {
           writeEventsFromCache();
        }
    }

    private void writeEventsFromCache(){
        for (Event message: cache) {
            super.logEvent(message);
        }
        clear();
    }

    private void clear(){
        cache.clear();
        cacheSize = 0;
    }

    @PreDestroy
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
