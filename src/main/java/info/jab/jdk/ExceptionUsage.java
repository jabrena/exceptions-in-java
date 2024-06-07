package info.jab.jdk;

import static java.util.function.Predicate.not;

import info.jab.jdk.utils.IOUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ExceptionUsage {

    public List<Path> countUsage(List<String> jdkList, String exception) {
        BiPredicate<Path, String> containsString = (path, pattern) -> path.toString().contains(pattern);

        Predicate<Path> isLocatedInTests = path -> {
            final String pattern = "/test/";
            return containsString.test(path, pattern);
        };

        Predicate<Path> isJavaFile = path -> {
            final String pattern = ".java";
            return containsString.test(path, pattern);
        };

        BiPredicate<Path, String> detectExceptionUsage = (param, ex) -> {
            return IOUtils.loadFileFromGitModule.apply(param).contains(ex);
        };

        // @formatter:off
        return jdkList.stream()
            .flatMap(IOUtils.getFilesFromPath)
            .filter(p -> !p.toFile().isDirectory())
            .filter(not(isLocatedInTests))
            .filter(isJavaFile)
            .filter(p -> detectExceptionUsage.test(p, exception))
            //.peek(System.out::println)
            .toList();
        // @formatter:on
    }
}
