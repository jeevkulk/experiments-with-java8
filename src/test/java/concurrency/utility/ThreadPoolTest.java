package concurrency.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class ThreadPoolTest {

    Logger logger = LogManager.getLogger(ThreadPoolTest.class);

    private ThreadPool threadPool = null;

    @Before
    public void setup() {
        threadPool = new ThreadPool();
    }

    @Test
    public void testGetResource() {
        Assert.assertEquals("Thread-1", threadPool.getResource().getName());
        Assert.assertEquals("Thread-2", threadPool.getResource().getName());
        Assert.assertEquals("Thread-3", threadPool.getResource().getName());
        Assert.assertEquals("Thread-4", threadPool.getResource().getName());
        Assert.assertEquals("Thread-5", threadPool.getResource().getName());
        Assert.assertEquals("Thread-6", threadPool.getResource().getName());
        Assert.assertEquals("Thread-7", threadPool.getResource().getName());
        Assert.assertEquals("Thread-8", threadPool.getResource().getName());
        Assert.assertEquals("Thread-9", threadPool.getResource().getName());
        Assert.assertEquals("Thread-10", threadPool.getResource().getName());
        Assert.assertNull(threadPool.getResource());
    }

    @Test
    public void testCloseResource() {
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 12; i++) {
            Future<?> future = service.submit(() -> {
                Thread thread = threadPool.getResource();
                logger.info(thread.getName());
                try {
                    Thread.currentThread().sleep(75000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.closeResource(thread);
            });
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            service.awaitTermination(600, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
