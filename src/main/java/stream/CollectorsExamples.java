package stream;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExamples {

    public String getLongestName(Stream<String> names) {
        String maxLengthName = names.max(
                (name1, name2) -> {
                    return name1.length() > name2.length() ? 1 : name1.length() < name2.length() ? -1 : 0;
                }
        ).get();
        return maxLengthName;
    }

    public Map<String, Long> getNameCount(Stream<String> names) {
        Map<String, Long> nameCount = names.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return nameCount;
    }
}
