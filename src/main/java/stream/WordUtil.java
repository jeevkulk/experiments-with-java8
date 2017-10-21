package stream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtil {

    public void findWordsContaining(String pattern) {
        try (Stream<String> words = Files.lines(getFilePath())) {
            words.filter((word) -> word.contains(pattern))
                    .forEach(System.out::println);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void findAlphabetWiseWordCount() {
        try (Stream<String> words = Files.lines(getFilePath())) {
            words.map(s -> s.substring(0,1))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .forEach((k, v) -> {
                            System.out.println(k+"-"+v);
                        });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void findAlphabetWiseWordList() {
        try (Stream<String> words = Files.lines(getFilePath())) {
            Map<String, List<String>> map = words
                    .collect(Collectors.groupingBy(s -> s.substring(0,1), Collectors.toList()));
            map.forEach((k, v) -> System.out.println(k+"="+v.toString()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private Path getFilePath() {
        Path path = null;
        try {
            path = Paths.get(this.getClass().getClassLoader().getResource("words.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}
