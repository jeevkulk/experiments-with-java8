package stream;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FindFactorialTest {

    private Logger logger = org.apache.logging.log4j.LogManager.getLogger(FindFactorialTest.class);

    private FindFactorial findFactorial = new FindFactorial();

    @Test
    public void testFindFactorial() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Long> factorials = findFactorial.calculateFactorial(Arrays.<Integer>asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        factorials.forEach(System.out::println);
        stopWatch.stop();
        logger.info(stopWatch);
    }
}
