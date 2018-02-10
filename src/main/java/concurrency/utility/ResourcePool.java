package concurrency.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ResourcePool<T> {

    Logger logger = LogManager.getLogger(ResourcePool.class);

    private ConcurrentLinkedQueue<T> concurrentLinkedQueuePool;
    private Semaphore countingSemaphore = null;
    private int minimumSize;
    private int maximumSize;
    private AtomicInteger currentSize;
    private long validationInterval;
    private TimeUnit validationIntervalTimeUnit;
    private ScheduledExecutorService scheduledExecutorService;

    private static final int DEFAULT_TIMEOUT = 5;
    private static final int DEFAULT_MINIMUM_SIZE = 5;
    private static final int DEFAULT_MAXIMUM_SIZE = 10;
    private static final long DEFAULT_VALIDATION_INTERVAL = 60;

    public ResourcePool(int minimumSize, int maximumSize, long validationInterval, TimeUnit validationIntervalTimeUnit) {
        this.minimumSize = minimumSize;
        this.maximumSize = maximumSize;
        this.validationInterval = validationInterval;
        this.validationIntervalTimeUnit = validationIntervalTimeUnit;

        currentSize = new AtomicInteger();
        countingSemaphore = new Semaphore(minimumSize);
        concurrentLinkedQueuePool = new ConcurrentLinkedQueue();
        initialize();
    }

    public ResourcePool() {
        this(DEFAULT_MINIMUM_SIZE, DEFAULT_MAXIMUM_SIZE, DEFAULT_VALIDATION_INTERVAL, TimeUnit.SECONDS);
    }

    private void initialize() {
        for (int i = 0; i < minimumSize; i++) {
            addResource();
        }
        Runnable scheduledTask = () -> {
            validate();
        };
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(scheduledTask, validationInterval, validationInterval, validationIntervalTimeUnit);
    }

    private void addResource() {
        addResource(create());
    }

    private void addResource(T t) {
        currentSize.incrementAndGet();
        concurrentLinkedQueuePool.add(t);
    }

    private void validate() {
        logger.info("Before validate: currentSize="+currentSize.get()+"; availablePermits="+countingSemaphore.availablePermits());
        while (currentSize.get() > minimumSize && countingSemaphore.availablePermits() > 0) {
            close(concurrentLinkedQueuePool.peek());
            currentSize.decrementAndGet();
        }
        concurrentLinkedQueuePool.forEach((t) -> {
            if (!validate(t)) {
                close(t);
                currentSize.decrementAndGet();
            }
        });
        while (currentSize.get() < minimumSize) {
            addResource();
        }
        logger.info("After validate: currentSize="+currentSize.get()+"; availablePermits="+countingSemaphore.availablePermits());
    }

    public T getResource() {
        T t = null;
        try {
            if (countingSemaphore.tryAcquire(DEFAULT_TIMEOUT, TimeUnit.SECONDS)) {
                t = concurrentLinkedQueuePool.poll();
            } else if (currentSize.get() < maximumSize) {
                addResource();
                countingSemaphore.release();
                return getResource();
            }
        } catch (InterruptedException ex) {
            logger.info("Interrupted Exception: "+ex);
        }
        return t;
    }

    public void closeResource(T t) {
        logger.info("In closeResource for "+t.toString());
        if (validate(t)) {
            addResource(t);
        }
        countingSemaphore.release();
    }

    public void shutdown() {
        concurrentLinkedQueuePool.forEach((t) -> {
            close(t);
        });
        scheduledExecutorService.shutdown();
    }

    public abstract T create();

    public abstract boolean validate(T t);

    public abstract void close(T t);
}
