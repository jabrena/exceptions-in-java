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

        ExceptionCounter playlistGenerator = new ExceptionCounter();

        final List<String> jdk6 = List.of("./jdk6/");
        var jdk6Results = playlistGenerator.countExceptions(jdk6);

        final List<String> jdk7 = List.of("./jdk7/");
        var jdk7Results = playlistGenerator.countExceptions(jdk7);

        final List<String> jdk8 = List.of("./jdk8/");
        var jdk8Results = playlistGenerator.countExceptions(jdk8);

        final List<String> jdk9 = List.of("./jdk9/");
        var jdk9Results = playlistGenerator.countExceptions(jdk9);

        final List<String> jdk10 = List.of("./jdk10/");
        var jdk10Results = playlistGenerator.countExceptions(jdk10);

        final List<String> jdk11 = List.of("./jdk11/");
        var jdk11Results = playlistGenerator.countExceptions(jdk11);

        final List<String> jdk12 = List.of("./jdk12/");
        var jdk12Results = playlistGenerator.countExceptions(jdk12);

        final List<String> jdk13 = List.of("./jdk13/");
        var jdk13Results = playlistGenerator.countExceptions(jdk13);

        final List<String> jdk14 = List.of("./jdk14/");
        var jdk14Results = playlistGenerator.countExceptions(jdk14);

        final List<String> jdk15 = List.of("./jdk15/");
        var jdk15Results = playlistGenerator.countExceptions(jdk15);

        final List<String> jdk16 = List.of("./jdk16/");
        var jdk16Results = playlistGenerator.countExceptions(jdk16);

        /*
        final List<String> jdk17 = List.of("./jdk17/");
        var jdk17Results = playlistGenerator.countExceptions(jdk17);

        final List<String> jdk18 = List.of("./jdk18/");
        var jdk18Results = playlistGenerator.countExceptions(jdk18);

        final List<String> jdk19 = List.of("./jdk19/");
        var jdk19Results = playlistGenerator.countExceptions(jdk19);

        final List<String> jdk20 = List.of("./jdk20/");
        var jdk20Results = playlistGenerator.countExceptions(jdk20);

        final List<String> jdk21 = List.of("./jdk21/");
        var jdk21Results = playlistGenerator.countExceptions(jdk21);

        final List<String> jdk22 = List.of("./jdk22/");
        var jdk22Results = playlistGenerator.countExceptions(jdk22);

         */

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
        /*
        System.out.println(jdk17Results);
        System.out.println(jdk18Results);
        System.out.println(jdk19Results);
        System.out.println(jdk20Results);
        System.out.println(jdk21Results);
        System.out.println(jdk22Results);

         */
    }
}
