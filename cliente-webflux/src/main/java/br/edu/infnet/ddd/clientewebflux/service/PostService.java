package br.edu.infnet.ddd.clientewebflux.service;

import br.edu.infnet.ddd.clientewebflux.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    private final WebClient webClient;

    public PostService(@Value("${api.base.url}") String baseUrl, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Flux<Post> getAllPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }

    public Mono<Post> getPostById(Long id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Flux<Post> getPostsByUserId(Long userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/posts")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToFlux(Post.class);
    }
}
