package info.jab.jdk;

import static org.assertj.core.api.Assertions.assertThat;

import info.jab.jdk.ExceptionCounter.Counter;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExceptionCounterTest {

    @Test
    void test() {
        //Given
        var jdks = List.of("./jdk22");
        Counter expectedCounter = new Counter("./jdk22", 120L, 122L);

        //When
        ExceptionCounter counter = new ExceptionCounter();
        var result = counter.countExceptions(jdks);

        //Then
        System.out.println(result);
        assertThat(result).isEqualTo(expectedCounter);
    }
}
