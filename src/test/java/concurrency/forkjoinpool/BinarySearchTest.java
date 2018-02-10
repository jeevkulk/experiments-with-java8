package concurrency.forkjoinpool;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BinarySearchTest {

    BinarySearch binarySearch = null;

    @Test
    public void testBinarySearch() {
        int[] arr = new int[]{1, 23, 42, 53, 64, 77, 22, 55, 223, 664, 663, 22, 67, 33, 222, 22, 778, 182};
        binarySearch = new BinarySearch();
        boolean found = binarySearch.doSearch(arr, 182);
        Assert.assertTrue(found);
    }

}
