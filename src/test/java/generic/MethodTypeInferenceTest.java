package generic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MethodTypeInferenceTest {

    MethodTypeInference methodTypeInference = new MethodTypeInference();

    @Test
    public void testCopyArray() {
        List<String> stringList1 = new ArrayList<>(Arrays.asList("Tree","Plant"));
        List<String> stringList2 = new ArrayList<>(Arrays.asList("Climber","Shrub"));

        //Type parameter <String> is optional here
        methodTypeInference.<String>copyList(stringList1, stringList2);
    }

    @Test
    public void testStaticCopyArray() {
        List<String> stringList1 = new ArrayList<>(Arrays.asList("Tree","Plant"));
        List<String> stringList2 = new ArrayList<>(Arrays.asList("Climber","Shrub"));

        //Type parameter <String> is optional here
        List<String> list = MethodTypeInference.<String>staticCopyList(stringList1, stringList2);
    }

}
