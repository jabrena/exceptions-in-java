package info.jab.ex;

import static org.assertj.core.api.Assertions.assertThat;

import info.jab.ex.ExceptionFinder.ExceptionDetail;
import info.jab.ex.ExceptionFinder.ExceptionTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExceptionFinderTest {

    private static ExceptionFinder counter;

    @BeforeAll
    private static void before() {
        counter = new ExceptionFinder();
    }

    @Test
    void should_countExceptions() {
        //Given
        var jdks = List.of("jdk22");

        //When
        var result = counter.countExceptions(jdks);

        //Then
        // @formatter:off
        var checkedCounter = result.stream()
            .filter(exceptionDetail -> exceptionDetail.type() == ExceptionTypes.CheckedException)
            .count();
        var uncheckedCounter = result.stream()
            .filter(exceptionDetail -> exceptionDetail.type() == ExceptionTypes.UncheckedException)
            .count();
        // @formatter:on
        assertThat(checkedCounter).isEqualTo(120L);
        assertThat(uncheckedCounter).isEqualTo(122L);
    }

    @Test
    void should_show_exceptions_from_module_base() {
        //Given
        var jdks = List.of("jdk22");

        //When
        var result = counter.countExceptions(jdks);

        //Then
        // @formatter:off
        var javaBaseCounter = result.stream()
            .filter(ed -> ed.javaModule().contains("base"))
            .peek(System.out::println)
            .count();
        // @formatter:on
        assertThat(javaBaseCounter).isEqualTo(72L);
    }

    @Test
    void should_group_exceptions_by_javaModule() {
        //Given
        var jdks = List.of("jdk22");

        //When
        var result = counter.countExceptions(jdks);

        //Then
        // @formatter:off
        Map<String, Long> fieldNamesByModule = result.stream()
            .collect(Collectors.groupingBy(ExceptionDetail::javaModule, Collectors.counting()));
        // @formatter:on

        record ModuleNameCount(String moduleName, Long count) {}

        List<ModuleNameCount> moduleNameList = new ArrayList<>(fieldNamesByModule.size());

        for (Map.Entry<String, Long> entry : fieldNamesByModule.entrySet()) {
            moduleNameList.add(new ModuleNameCount(entry.getKey(), entry.getValue()));
        }

        System.out.println("All exceptions:");
        // @formatter:off
        moduleNameList.stream()
            .sorted((a, b) -> b.count().compareTo(a.count()))
            .forEach(System.out::println);
        // @formatter:on
    }
}
