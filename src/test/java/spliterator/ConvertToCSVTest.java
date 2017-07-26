package spliterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConvertToCSVTest {

    private ConvertToCSV convertToCSV;

    @Before
    public void setup() {
        convertToCSV = new ConvertToCSV();
    }

    @Test
    public void testCountWords() {
        convertToCSV.execute();
        Mockito.times(1);
    }
}
