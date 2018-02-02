package stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GroupingByExampleTest {

    private GroupingByExample groupingByExample;

    @Before
    public void setup() {
        groupingByExample = new GroupingByExample();
    }

    @Test
    public void averageByCountryAndCreditRating() {
        groupingByExample.averageByCountryAndCreditRating();
        Mockito.times(1);
    }
}
