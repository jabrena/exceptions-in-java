package info.jab.jdk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionCounter {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCounter.class);

    record Counter(String jdk, Long checked, Long unchecked) {}

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

        Function<List<String>, Long> countCheckedExceptions = param -> {
            logger.info("Counting Checked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().contains("Exception.java"))
                .filter(containsCheckedExceptionPattern)
                //.peek(System.out::println)
                .count();
        };

        Function<List<String>, Long> countUncheckedExceptions = param -> {
            logger.info("Counting Unchecked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.getFileName().toString().contains("Exception.java"))
                .filter(containsUncheckedExceptionPattern)
                //.peek(System.out::println)
                .count();
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
