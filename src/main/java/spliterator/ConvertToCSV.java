package spliterator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ConvertToCSV {

    public void execute() {
        Path path = null;
        try {
            path = Paths.get(this.getClass().getClassLoader().getResource("words.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try(Stream<String> lines = Files.lines(path)) {
            final StringBuilder sb = new StringBuilder();
            final Spliterator<String> wordSpliterator = lines.spliterator();
            Spliterator<StringBuilder> sbSpliterator = new Spliterator<StringBuilder>() {
                @Override
                public boolean tryAdvance(Consumer<? super StringBuilder> action) {
                    if (wordSpliterator.tryAdvance(str -> sb.append(str).append(","))) {
                        return true;
                    } else {
                        action.accept(sb);
                        return false;
                    }
                }

                @Override
                public Spliterator<StringBuilder> trySplit() {
                    return null;
                }

                @Override
                public long estimateSize() {
                    return wordSpliterator.estimateSize();
                }

                @Override
                public int characteristics() {
                    return wordSpliterator.characteristics();
                }
            };
            sbSpliterator.forEachRemaining(System.out::println);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
