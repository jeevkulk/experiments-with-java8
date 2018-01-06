package stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WordUtilTest {

    private WordUtil wordUtil;

    @Before
    public void setup() {
        wordUtil = new WordUtil();
    }

    @Test
    public void testFindAlphabetWiseWordCount() {
        wordUtil.findAlphabetWiseWordCount();
        Mockito.times(1);
    }

    @Test
    public void testFindAlphabetWiseWordList() {
        wordUtil.findAlphabetWiseWordList();
        Mockito.times(1);
    }

    @Test
    public void testFindWordsContaining() {
        wordUtil.findWordsContaining("cat");
        Mockito.times(1);
    }

    @Test
    public void averageByCountryAndCreditRating() {
        wordUtil.averageByCountryAndCreditRating();
        Mockito.times(1);
    }
}
