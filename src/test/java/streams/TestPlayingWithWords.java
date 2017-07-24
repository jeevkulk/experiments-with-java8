package streams;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestPlayingWithWords {

    private PlayingWithWords playingWithWords;

    @Before
    public void setup() {
        playingWithWords = new PlayingWithWords();
    }

    @Test
    public void testCountWords() {
        playingWithWords.printWords();
        Mockito.times(1);
    }
}
