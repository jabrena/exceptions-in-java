package info.jab.ex;

import static org.assertj.core.api.Assertions.assertThat;

import info.jab.ex.ExceptionFinder.ExceptionDetail;
import info.jab.ex.ExceptionFinder.ExceptionTypes;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExceptionUsageTest {

    // @formatter:off
    @Test
    void should_list_usages_for_checkedExceptions_from_baseModule() {

        ExceptionFinder counter = new ExceptionFinder();
        ExceptionUsage usages = new ExceptionUsage();

        final List<String> jdks = List.of("jdk22");

        var baseModuleCheckedExceptions = counter.countExceptions(jdks).stream()
                .filter(ed -> ed.javaModule().contains("base"))
                .filter(ed -> ed.type() == ExceptionTypes.CheckedException)
                .map(ExceptionDetail::name)
                .map(str -> str.replace(".java", ""))
                .toList();

        record ExceptionUsageDetail(String name, Integer usage) {}

        var list = baseModuleCheckedExceptions.stream()
            .parallel()
            .map(ex -> {
                var count = usages.countUsage(jdks, ex).size();
                return new ExceptionUsageDetail(ex, count);
            })
            .toList();
        
        System.out.println("Checked Exception list");
        var sortList = list.stream()
            .sorted((a, b) -> b.usage().compareTo(a.usage()))
            .peek(System.out::println)
            .toList();
        
        assertThat(sortList.size()).isGreaterThan(0);
    }

    // @formatter:on

    // @formatter:off
    @Test
    void should_list_usages_for_uncheckedExceptions_from_baseModule() {

        ExceptionFinder counter = new ExceptionFinder();
        ExceptionUsage usages = new ExceptionUsage();

        final List<String> jdks = List.of("jdk22");

        var baseModuleUncheckedExceptions = counter.countExceptions(jdks).stream()
                .filter(ed -> ed.javaModule().contains("base"))
                .filter(ed -> ed.type() == ExceptionTypes.UncheckedException)
                .map(ExceptionDetail::name)
                .map(str -> str.replace(".java", ""))
                .toList();

        record ExceptionUsageDetail(String name, Integer usage) {}

        var list = baseModuleUncheckedExceptions.stream()
            .parallel()
            .map(ex -> {
                var count = usages.countUsage(jdks, ex).size();
                return new ExceptionUsageDetail(ex, count);
            })
            .toList();
            
        System.out.println("Unchecked Exception list");
        var sortList = list.stream()
            .sorted((a, b) -> b.usage().compareTo(a.usage()))
            .peek(System.out::println)
            .toList();
        
        assertThat(sortList.size()).isGreaterThan(0);
    }

    // @formatter:on

    // @formatter:off
    @Test
    void should_list_usages_for_top10_exceptions_from_baseModule() {

        ExceptionFinder counter = new ExceptionFinder();
        ExceptionUsage usages = new ExceptionUsage();

        final List<String> jdks = List.of("jdk22");

        var baseModuleExceptions = counter.countExceptions(jdks).stream()
                .filter(ed -> ed.javaModule().contains("base"))
                .toList();

        record ExceptionUsageDetail(ExceptionTypes type, String name, Integer usage) {}

        var list = baseModuleExceptions.stream()
            .parallel()
            .map(ed -> {
                var ex = ed.name().replace(".java", "");
                var count = usages.countUsage(jdks, ex).size();
                return new ExceptionUsageDetail(ed.type(), ex, count);
            })
            .toList();
            
        System.out.println("Exception list");
        var sortList = list.stream()
            .sorted((a, b) -> b.usage().compareTo(a.usage()))
            .peek(System.out::println)
            .limit(10)
            .toList();
        
        assertThat(sortList.size()).isGreaterThan(0);
    }
    // @formatter:on
}
