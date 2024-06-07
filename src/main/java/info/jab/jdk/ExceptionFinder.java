package info.jab.jdk;

import static java.util.function.Predicate.not;

import info.jab.jdk.utils.IOUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionFinder {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionFinder.class);

    public enum ExceptionTypes {
        CheckedException,
        UncheckedException,
    }

    public record ExceptionDetail(String jdk, ExceptionTypes type, String name, String javaModule) {}

    private static final Pattern SLASH_PATTERN = Pattern.compile("/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)");

    public List<ExceptionDetail> countExceptions(List<String> paths) {
        Predicate<Path> containsCheckedExceptionPattern = param -> {
            final String pattern = " extends Exception";
            return IOUtils.loadFileFromGitModule.apply(param).contains(pattern);
        };

        Predicate<Path> containsUncheckedExceptionPattern = param -> {
            final String pattern = " extends RuntimeException";
            return IOUtils.loadFileFromGitModule.apply(param).contains(pattern);
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

        BiFunction<List<String>, ExceptionTypes, List<ExceptionDetail>> findExceptions = (param, typeEx) -> {
            var stream = param.stream().flatMap(IOUtils.getFilesFromPath).filter(isExceptionFile).filter(not(isLocatedInTests));

            if (typeEx == ExceptionTypes.CheckedException) {
                logger.info("Retrieving Checked Exceptions");

                return stream
                    .filter(containsCheckedExceptionPattern)
                    .map(String::valueOf)
                    .map(str -> toExceptionDetail.apply(str, ExceptionTypes.CheckedException))
                    .toList();
            } else {
                logger.info("Retrieving Unchecked Exceptions");

                return stream
                    .filter(containsUncheckedExceptionPattern)
                    .map(String::valueOf)
                    .map(str -> toExceptionDetail.apply(str, ExceptionTypes.UncheckedException))
                    .toList();
            }
        };

        Function<List<String>, List<ExceptionDetail>> compute = param -> {
            List<ExceptionDetail> counter1 = findExceptions.apply(param, ExceptionTypes.CheckedException);
            List<ExceptionDetail> counter2 = findExceptions.apply(param, ExceptionTypes.UncheckedException);
            return Stream.concat(counter1.stream(), counter2.stream()).toList();
        };

        Function<List<String>, List<ExceptionDetail>> stopWatch = param -> {
            logger.info("Starting the process");

            long startTime = System.currentTimeMillis();

            var result = compute.apply(param);

            long stopTime = System.currentTimeMillis();
            float elapsedTime = (stopTime - startTime) / 1000f;

            logger.info("Computation time: {} seconds", elapsedTime);

            return result;
        };

        return stopWatch.apply(paths);
    }
}
