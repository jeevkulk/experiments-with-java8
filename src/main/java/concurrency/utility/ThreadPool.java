package concurrency.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadPool extends ResourcePool<Thread> {

    Logger logger = LogManager.getLogger(ThreadPool.class);

    @Override
    public Thread create() {
        return new Thread();
    }

    @Override
    public boolean validate(Thread thread) {
        return thread.isAlive();
    }

    @Override
    public void close(Thread thread) {
        thread = null;
    }
}
