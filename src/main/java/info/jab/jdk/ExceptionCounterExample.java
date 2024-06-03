package info.jab.jdk;

import com.github.lalyos.jfiglet.FigletFont;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionCounterExample {

    public static void main(String... args) {
        Consumer<String> showHeader = param -> {
            try {
                String asciiArt = FigletFont.convertOneLine(param);
                System.out.println(asciiArt);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(), e);
            }
        };

        showHeader.accept("OpenJDK");

        ExceptionCounter counter = new ExceptionCounter();

        showLatestData(counter);
        //showAllData(counter);
    }

    private static void showLatestData(ExceptionCounter counter) {
        final List<String> jdk22 = List.of("./jdk22/");
        var jdk22Results = counter.countExceptions(jdk22);
    }

    private static void showAllData(ExceptionCounter counter) {
        final List<String> jdk6 = List.of("./jdk6/");
        var jdk6Results = counter.countExceptions(jdk6);

        final List<String> jdk7 = List.of("./jdk7/");
        var jdk7Results = counter.countExceptions(jdk7);

        final List<String> jdk8 = List.of("./jdk8/");
        var jdk8Results = counter.countExceptions(jdk8);

        final List<String> jdk9 = List.of("./jdk9/");
        var jdk9Results = counter.countExceptions(jdk9);

        final List<String> jdk10 = List.of("./jdk10/");
        var jdk10Results = counter.countExceptions(jdk10);

        final List<String> jdk11 = List.of("./jdk11/");
        var jdk11Results = counter.countExceptions(jdk11);

        final List<String> jdk12 = List.of("./jdk12/");
        var jdk12Results = counter.countExceptions(jdk12);

        final List<String> jdk13 = List.of("./jdk13/");
        var jdk13Results = counter.countExceptions(jdk13);

        final List<String> jdk14 = List.of("./jdk14/");
        var jdk14Results = counter.countExceptions(jdk14);

        final List<String> jdk15 = List.of("./jdk15/");
        var jdk15Results = counter.countExceptions(jdk15);

        final List<String> jdk16 = List.of("./jdk16/");
        var jdk16Results = counter.countExceptions(jdk16);

        final List<String> jdk17 = List.of("./jdk17/");
        var jdk17Results = counter.countExceptions(jdk17);

        final List<String> jdk18 = List.of("./jdk18/");
        var jdk18Results = counter.countExceptions(jdk18);

        final List<String> jdk19 = List.of("./jdk19/");
        var jdk19Results = counter.countExceptions(jdk19);

        final List<String> jdk20 = List.of("./jdk20/");
        var jdk20Results = counter.countExceptions(jdk20);

        final List<String> jdk21 = List.of("./jdk21/");
        var jdk21Results = counter.countExceptions(jdk21);

        final List<String> jdk22 = List.of("./jdk22/");
        var jdk22Results = counter.countExceptions(jdk22);

        //Results
        System.out.println(jdk6Results);
        System.out.println(jdk7Results);
        System.out.println(jdk8Results);
        System.out.println(jdk9Results);
        System.out.println(jdk10Results);
        System.out.println(jdk11Results);
        System.out.println(jdk12Results);
        System.out.println(jdk13Results);
        System.out.println(jdk14Results);
        System.out.println(jdk15Results);
        System.out.println(jdk16Results);
        System.out.println(jdk17Results);
        System.out.println(jdk18Results);
        System.out.println(jdk19Results);
        System.out.println(jdk20Results);
        System.out.println(jdk21Results);
        System.out.println(jdk22Results);
    }
}
