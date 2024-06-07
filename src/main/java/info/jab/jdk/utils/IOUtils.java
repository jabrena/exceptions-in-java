package info.jab.jdk.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

    private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static Function<Path, String> loadFileFromGitModule = param -> {
        try {
            return new String(Files.readAllBytes(param), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(param.toString());
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    };

    public static Function<String, Stream<Path>> getFilesFromPath = param -> {
        try {
            return Files.walk(Paths.get(param));
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    };
}
