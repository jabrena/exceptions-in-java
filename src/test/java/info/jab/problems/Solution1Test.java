package info.jab.problems;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Solution1Test {

    private static WireMockServer wireMockServer;

    @BeforeAll
    private static void setup() {
        // Start WireMock server on a dynamic port
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        // Configure WireMock
        configureFor("localhost", wireMockServer.port());

        // Stub the response
        // @formatter:off
        stubFor(get(urlEqualTo("/"))
            .willReturn(aResponse()
            .withHeader("Content-Type", "text/html")
            .withBody("<html><body>Hello, World!</body></html>"))
        );
        // @formatter:on
    }

    @AfterAll
    public static void teardown() {
        // Stop WireMock server
        wireMockServer.stop();
    }

    @Test
    void should_work() {
        //Given
        String address = "http://localhost:" + wireMockServer.port();

        //When
        Solution1 solution = new Solution1();
        var result = solution.extractHTML(address);

        //Then
        assertThat(result).isNotEmpty();
    }

    @Test
    void should_not_work() {
        //Given
        String address = "https://www.google999.com";

        //When
        Solution1 solution = new Solution1();
        var result = solution.extractHTML(address);

        //Then
        assertThat(result).isEmpty();
    }
}
