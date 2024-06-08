package info.jab.ex;

import com.github.lalyos.jfiglet.FigletFont;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionFinderExample {

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

        ExceptionFinder counter = new ExceptionFinder();

        final List<String> jdk22 = List.of("jdk22");
        var jdk22Results = counter.countExceptions(jdk22);
        jdk22Results.stream().forEach(System.out::println);
    }
}
