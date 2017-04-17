package exapmle.loggers;

import exapmle.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private List<Event> cache;
    private int cacheSize;

    public CacheFileEventLogger(String filename,String encoding ,int cacheSize){
        super(filename,encoding);
        this.cache = new ArrayList<Event>(cacheSize);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event message) {
        cache.add(message);

        if(cache.size() >= cacheSize) {
           writeEventsFromCache();
           clear();
        }
    }

    private void writeEventsFromCache(){
        for (Event message: cache) {
            super.logEvent(message);
        }
    }

    private void clear(){
        cache.clear();
        cacheSize = 0;
    }

    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
