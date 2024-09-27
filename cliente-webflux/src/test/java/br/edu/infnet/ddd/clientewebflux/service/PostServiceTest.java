package br.edu.infnet.ddd.clientewebflux.service;

import br.edu.infnet.ddd.clientewebflux.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Testcontainers
class PostServiceTest {

    @Container
    public static MockServerContainer mockServer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver:mockserver-5.11.2"));

    private PostService postService;
    private MockServerClient mockServerClient;

    @BeforeEach
    void setUp() {
        mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());
        String baseUrl = "http://" + mockServer.getHost() + ":" + mockServer.getServerPort();
        WebClient.Builder webClientBuilder = WebClient.builder();
        postService = new PostService(baseUrl, webClientBuilder);
    }

    @Test
    void testGetAllPosts() {
        // Configurar o MockServer para retornar uma resposta simulada
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

        // Testar o método getAllPosts do PostService
        Flux<Post> posts = postService.getAllPosts();

        // Verificar o resultado
        StepVerifier.create(posts)
                .expectNextMatches(post -> 
                    post.getId() == 1 &&
                    post.getUserId() == 1 &&
                    "Test Post".equals(post.getTitle()) &&
                    "This is a test post".equals(post.getBody())
                )
                .expectComplete()
                .verify();
    }

    // Adicione mais testes para outros métodos do PostService
}
