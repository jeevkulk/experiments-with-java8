package stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class CollectorsExamplesTest {

    private CollectorsExamples collectorsExamples;

    @Before
    public void setup() {
        collectorsExamples = new CollectorsExamples();
    }

    @Test
    public void testGetLongestName() {
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison",
                "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        String name = collectorsExamples.getLongestName(names);
        Assert.assertEquals("Stuart Sutcliffe", name);
    }

    @Test
    public void testNameCount() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> map = collectorsExamples.getNameCount(names);
        Assert.assertEquals((long)3L, (long)map.get("John"));
        Assert.assertEquals((long)2L, (long)map.get("Paul"));
        Assert.assertEquals((long)1L, (long)map.get("George"));
    }
}
