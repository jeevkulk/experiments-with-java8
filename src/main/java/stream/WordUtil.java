package stream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtil {

    public void findWordsContaining(String pattern) {
        try (Stream<String> words = Files.lines(getFilePath("words.txt"))) {
            words.filter((word) -> word.contains(pattern))
                    .forEach(System.out::println);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void findAlphabetWiseWordCount() {
        try (Stream<String> words = Files.lines(getFilePath("words.txt"))) {
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
        try (Stream<String> words = Files.lines(getFilePath("words.txt"))) {
            Map<String, List<String>> map = words
                    .collect(Collectors.groupingBy(s -> s.substring(0,1), Collectors.toList()));
            map.forEach((k, v) -> System.out.println(k+"="+v.toString()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void averageByCountryAndCreditRating() {
        try (Stream<String> lines = Files.lines(getFilePath("FILE.DAT"))) {
            Map<String, String[]> dataMap = lines.map(line -> line.split("/t"))
                                        .collect(Collectors.toMap(line -> line[1],
                                                line -> line));

            dataMap.entrySet().forEach(System.out::println);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void parallelProcessingOfWords() {
        try (Stream<String> words = Files.lines(getFilePath("words.txt"))) {
            words.parallel().count();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private Path getFilePath(String filename) {
        Path path = null;
        try {
            path = Paths.get(this.getClass().getClassLoader().getResource(filename).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}
