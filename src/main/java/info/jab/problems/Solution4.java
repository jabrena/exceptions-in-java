package info.jab.problems;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

public class Solution4 {

    public static void main(String[] args) {
        String urlString = "https://www.google.com";

        Function<String, URI> toUri = param -> {
            return URI.create(param);
        };

        Function<URI, String> toHTML = param -> {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(param).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException();
            }
        };

        var result = toUri.andThen(toHTML).apply(urlString);

        System.out.println(result);
    }
}
