package dam.spring.defenation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;


public class Reservoir<T> {
    private long duration = 10;
    private long size = 1000;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    private static final Logger logger = LoggerFactory.getLogger(Reservoir.class);

    @PostConstruct
    private void init() {

    }
}