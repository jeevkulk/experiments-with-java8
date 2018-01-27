package stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtil {

    private Map<String, BigDecimal> euroConversionMap;

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
            words.map(s -> s.substring(0, 1))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .forEach((k, v) -> {
                        System.out.println(k + "-" + v);
                    });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void findAlphabetWiseWordList() {
        try (Stream<String> words = Files.lines(getFilePath("words.txt"))) {
            Map<String, List<String>> map = words
                    .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
            map.forEach((k, v) -> System.out.println(k + "=" + v.toString()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void averageByCountryAndCreditRating() {
        getCurrencyConversionRates();
        try (Stream<String> lines = Files.lines(getFilePath("FILE.DAT"))) {
            lines.skip(1)
                    .map(line -> {
                        String[] str = line.split("\t");
                        CreditRating cr = new CreditRating(str[0], str[1], str[2], str[3], str[4], str[5], isNull(str[6]) ? new BigDecimal("0") : new BigDecimal(str[6]));
                        return cr;
                    }
                    )
                    .collect(
                            Collectors.groupingBy
                                    (
                                            cr -> {
                                                String keyStr = !isNull(cr.getCountry()) ? cr.getCountry() : cr.getCity();
                                                return keyStr;
                                            },
                                            Collectors.groupingBy(
                                                    CreditRating::getCreditRating, Collectors.averagingDouble((cr) -> getAmountInEuro(cr).doubleValue())
                                            )
                                    )
                    )
                    .entrySet()
                    .forEach(System.out::println);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private boolean isNull(String str) {
        return str == null || "".equals(str) ? true : false;
    }

    private BigDecimal getAmountInEuro(CreditRating cr) {
        return cr.getAmount().multiply(euroConversionMap.get(cr.getCurrency()), new MathContext(2));
    }

    private void getCurrencyConversionRates() {
        euroConversionMap = new HashMap<>();
        MathContext mc = new MathContext(2);

        BigDecimal usdToEuroConversionRate = new BigDecimal(1).divide(new BigDecimal(1.35), 2, RoundingMode.HALF_UP);
        BigDecimal gbpToEuroConversionRate = usdToEuroConversionRate.multiply(new BigDecimal(1.654), mc);
        BigDecimal chfToEuroConversionRate = usdToEuroConversionRate.multiply(new BigDecimal(1.1), mc);

        euroConversionMap.put("USD", usdToEuroConversionRate);
        euroConversionMap.put("GBP", gbpToEuroConversionRate);
        euroConversionMap.put("CHF", chfToEuroConversionRate);
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

class CreditRating {

    private String companyCode;
    private String account;
    private String city;
    private String country;
    private String creditRating;
    private String currency;
    private BigDecimal amount;

    public CreditRating(String companyCode, String account, String city, String country, String creditRating, String currency, BigDecimal amount) {
        this.companyCode = companyCode;
        this.account = account;
        this.city = city;
        this.country = country;
        this.creditRating = creditRating;
        this.currency = currency;
        this.amount = amount;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}