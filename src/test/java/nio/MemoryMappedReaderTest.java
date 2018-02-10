package nio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemoryMappedReaderTest {

    private MemoryMappedReader memoryMappedReader;
    private final String filename = "E:\\technology_workspace\\data\\surnames.csv";

    @Before
    public void setup() {
        memoryMappedReader = new MemoryMappedReader();
    }

    @Test
    public void testMemoryMappedReader() {
        memoryMappedReader.read(filename);
        Mockito.times(1);
    }
}
