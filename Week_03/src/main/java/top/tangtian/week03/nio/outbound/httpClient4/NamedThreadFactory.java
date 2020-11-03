package top.tangtian.week03.nio.outbound.httpClient4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangtian
 * @version 1.0
 * @className NamedThreadFactory
 * @description
 * @date 2020/10/31 3:55 PM
 **/
public class NamedThreadFactory implements ThreadFactory {
    private static final Logger LOG = LoggerFactory.getLogger(NamedThreadFactory.class);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;
    private final  boolean daemon;

    public NamedThreadFactory(String namePrefix, boolean daemon) {
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }
    public NamedThreadFactory(String namePrefix){
        this(namePrefix,false);
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group,r,namePrefix + "-thread-" + threadNumber.getAndIncrement(),0);
        t.setDaemon(daemon);
        return t;
    }
}
