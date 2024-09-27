package br.edu.infnet.ddd.clientewebflux.controller;

import br.edu.infnet.ddd.clientewebflux.model.Post;
import br.edu.infnet.ddd.clientewebflux.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Flux<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    

    @GetMapping("/{id}")
    public Mono<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/user/{userId}")
    public Flux<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }
}
