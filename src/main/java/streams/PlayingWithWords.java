package streams;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;

public class PlayingWithWords {

    public void printWords() {
        Path path = null;
        try {
            URL url = this.getClass().getClassLoader().getResource("commonwords.txt");
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try(Stream<String> lines = Files.lines(path)) {
            Spliterator<String> stringSpliterator = lines.spliterator();
            stringSpliterator.forEachRemaining(System.out::println);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
