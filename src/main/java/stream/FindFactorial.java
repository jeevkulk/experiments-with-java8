package stream;

import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class FindFactorial {

    private Logger logger = org.apache.logging.log4j.LogManager.getLogger(FindFactorial.class);

    public List<Long> calculateFactorial(List<Integer> numbers) {
        List<Long> factorials = numbers.stream().parallel()
                .map(n -> calculateFactorialRecursively(n))
                .collect(Collectors.toList());
        return factorials;
    }

    private long calculateFactorialRecursively(long number) {
        if (number < 1)
            return 1;
        logger.info("Thread name for "+number+" = "+Thread.currentThread().getName());
        return number * calculateFactorialRecursively(number - 1);
    }
}
