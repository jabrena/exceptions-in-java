package info.jab.jdk;

import static java.util.function.Predicate.not;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionCounter {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCounter.class);

    public record Counter(String jdk, Long checked, Long unchecked) {}

    public Counter countExceptions(List<String> paths) {
        Function<Path, String> loadFileFromGitModule = param -> {
            try {
                return new String(Files.readAllBytes(param), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        };

        Function<String, Stream<Path>> getFilesFromPath = param -> {
            try {
                return Files.walk(Paths.get(param));
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        };

        Predicate<Path> containsCheckedExceptionPattern = param -> {
            final String pattern = " extends Exception";
            return loadFileFromGitModule.apply(param).contains(pattern);
        };

        Predicate<Path> containsUncheckedExceptionPattern = param -> {
            final String pattern = " extends RuntimeException";
            return loadFileFromGitModule.apply(param).contains(pattern);
        };

        BiPredicate<Path, String> containsString = (path, pattern) -> path.toString().contains(pattern);

        Predicate<Path> isExceptionFile = path -> {
            final String pattern = "Exception.java";
            return containsString.test(path, pattern);
        };

        Predicate<Path> isLocatedInTests = path -> {
            final String pattern = "/test/";
            return containsString.test(path, pattern);
        };

        Function<List<String>, List<Path>> checkedExceptionsList = param -> {
            logger.info("Retrieving Checked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(Files::isRegularFile)
                .filter(isExceptionFile)
                .filter(not(isLocatedInTests))
                .filter(containsCheckedExceptionPattern)
                .peek(System.out::println)
                .toList();
        };

        Function<List<String>, Long> countCheckedExceptions = param -> {
            logger.info("Counting Checked Exceptions");
            return checkedExceptionsList.apply(paths).stream().count();
        };

        Function<List<String>, List<Path>> uncheckedExceptionsList = param -> {
            logger.info("Retrieving Unchecked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(isExceptionFile)
                .filter(not(isLocatedInTests))
                .filter(containsUncheckedExceptionPattern)
                .peek(System.out::println)
                .toList();
        };

        Function<List<String>, Long> countUncheckedExceptions = param -> {
            logger.info("Counting Unchecked Exceptions");
            return uncheckedExceptionsList.apply(param).stream().count();
        };

        long startTime = System.currentTimeMillis();

        var counter1 = countCheckedExceptions.apply(paths);
        var counter2 = countUncheckedExceptions.apply(paths);

        long stopTime = System.currentTimeMillis();
        float elapsedTime = (stopTime - startTime) / 1000;

        logger.info("Computation time in {}: {} seconds", paths.get(0), elapsedTime);

        return new Counter(paths.get(0), counter1, counter2);
    }
}
