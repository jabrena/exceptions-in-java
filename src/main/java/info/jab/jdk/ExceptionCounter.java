package info.jab.jdk;

import static java.util.function.Predicate.not;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionCounter {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCounter.class);

    public enum ExceptionTypes {
        CheckedException,
        UncheckedException,
    }

    public record ExceptionDetail(String jdk, ExceptionTypes type, String name, String javaModule) {}

    private static final Pattern SLASH_PATTERN = Pattern.compile("/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)");

    public List<ExceptionDetail> countExceptions(List<String> paths) {
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

        Function<String, String> extractJDK = param -> {
            int lastIndexOfSlash = param.indexOf("/");
            return param.substring(0, lastIndexOfSlash);
        };

        Function<String, String> extractModule = param -> {
            Matcher matcher = SLASH_PATTERN.matcher(param);
            if (matcher.find()) {
                String javaModule = matcher.group(2);
                return javaModule;
            } else {
                return String.valueOf("Unknown");
            }
        };

        Function<String, String> toExceptionClass = param -> {
            int lastIndexOfSlash = param.lastIndexOf("/");
            return param.substring(lastIndexOfSlash + 1);
        };

        BiFunction<String, ExceptionTypes, ExceptionDetail> toExceptionDetail = (param, type) -> {
            var jdk = extractJDK.apply(param);
            var exceptionName = toExceptionClass.apply(param);
            var javaModule = extractModule.apply(param);
            return new ExceptionDetail(jdk, type, exceptionName, javaModule);
        };

        Function<List<String>, List<ExceptionDetail>> checkedExceptionsList = param -> {
            logger.info("Retrieving Checked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(Files::isRegularFile)
                .filter(isExceptionFile)
                .filter(not(isLocatedInTests))
                .filter(containsCheckedExceptionPattern)
                .map(String::valueOf)
                .map(str -> toExceptionDetail.apply(str, ExceptionTypes.CheckedException))
                .peek(System.out::println)
                .toList();
        };

        Function<List<String>, List<ExceptionDetail>> uncheckedExceptionsList = param -> {
            logger.info("Retrieving Unchecked Exceptions");
            return param
                .stream()
                .flatMap(getFilesFromPath)
                .filter(isExceptionFile)
                .filter(not(isLocatedInTests))
                .filter(containsUncheckedExceptionPattern)
                .map(String::valueOf)
                .map(str -> toExceptionDetail.apply(str, ExceptionTypes.UncheckedException))
                .peek(System.out::println)
                .toList();
        };

        Consumer<String> stopWatch = param -> {
            logger.info("Starting the process");

            long startTime = System.currentTimeMillis();

            //process.accept();

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            logger.info("Computation time: {}", elapsedTime);
        };

        long startTime = System.currentTimeMillis();

        List<ExceptionDetail> counter1 = checkedExceptionsList.apply(paths);
        List<ExceptionDetail> counter2 = uncheckedExceptionsList.apply(paths);

        long stopTime = System.currentTimeMillis();
        float elapsedTime = (stopTime - startTime) / 1000f;

        logger.info("Computation time in {}: {} seconds", paths.get(0), elapsedTime);

        return Stream.concat(counter1.stream(), counter2.stream()).toList();
    }
}
