package br.edu.infnet.ddd.clientewebflux.controller;

import br.edu.infnet.ddd.clientewebflux.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class PostControllerIntegrationTest {

    @Container
    public static MockServerContainer mockServer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver:mockserver-5.11.2"));

    private MockServerClient mockServerClient;

    @Autowired
    private WebTestClient webTestClient;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public String apiBaseUrl() {
            return "http://" + mockServer.getHost() + ":" + mockServer.getServerPort();
        }
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("api.base.url", () -> "http://" + mockServer.getHost() + ":" + mockServer.getServerPort());
    }

    @BeforeEach
    void setUp() {
        mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());
    }

    @Test
    void testGetAllPosts() {
        mockServerClient.when(
                request()
                        .withMethod("GET")
                        .withPath("/posts")
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"userId\":1,\"title\":\"Test Post\",\"body\":\"This is a test post\"}]")
        );

        webTestClient.get().uri("/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Post.class)
                .hasSize(1)
                .contains(new Post(1L, 1L, "Test Post", "This is a test post"));
    }

    // Adicione mais testes para outros endpoints do PostController
}
