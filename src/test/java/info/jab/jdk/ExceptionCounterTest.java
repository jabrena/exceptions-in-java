package info.jab.jdk;

import static org.assertj.core.api.Assertions.assertThat;

import info.jab.jdk.ExceptionCounter.ExceptionTypes;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExceptionCounterTest {

    @Test
    void should_countExceptions() {
        //Given
        var jdks = List.of("jdk22");

        //When
        ExceptionCounter counter = new ExceptionCounter();
        var result = counter.countExceptions(jdks);

        //Then
        var checkedCounter = result.stream().filter(exceptionDetail -> exceptionDetail.type() == ExceptionTypes.CheckedException).count();
        var uncheckedCounter = result.stream().filter(exceptionDetail -> exceptionDetail.type() == ExceptionTypes.UncheckedException).count();
        assertThat(checkedCounter).isEqualTo(120L);
        assertThat(uncheckedCounter).isEqualTo(122L);
    }

    @Test
    void should_show_exceptions_from_module_base() {
        //Given
        var jdks = List.of("jdk22");

        //When
        ExceptionCounter counter = new ExceptionCounter();
        var result = counter.countExceptions(jdks);

        //Then
        System.out.println("Results:");
        var javaBaseCounter = result.stream().filter(ed -> ed.javaModule().contains("base")).peek(System.out::println).count();
        assertThat(javaBaseCounter).isEqualTo(72L);
    }
}
