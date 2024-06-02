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

        System.out.println(jdk8Results);
        System.out.println(jdk9Results);
        System.out.println(jdk10Results);
        System.out.println(jdk11Results);
        System.out.println(jdk12Results);
    }
}
