package concurrency.executor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class FindPrimeNumbersTest {

    private FindPrimeNumbers findPrimeNumbers;

    @Test
    public void testFindPrimeNumbers() {
        findPrimeNumbers = new FindPrimeNumbers(1, 50);
        Set<Integer> primeNumberSet = findPrimeNumbers.getPrimeNumbers();
        int[] primeNumbers = primeNumberSet.stream()
                                            .mapToInt(Integer::intValue)
                                            .toArray();
        int[] expectedNumbers = new int[]{1,2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};

        Assert.assertArrayEquals(expectedNumbers, primeNumbers);
    }
}
