package edu.mum.cs544.post.controller;

import edu.mum.cs544.post.dto.PostRequest;
import edu.mum.cs544.post.dto.PostResponse;
import edu.mum.cs544.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse getAll() {
        return postService.getAll();
    }

    @PostMapping("/{userId}")
    public PostResponse addPost(@PathVariable Integer userId, @Valid @RequestBody PostRequest postRequest) {
        return postService.addPost(userId, postRequest);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Integer id, @RequestBody PostRequest postRequest) {
        return postService.updatePost(id, postRequest);
    }

    @DeleteMapping("/{id}")
    public PostResponse deletePost(@PathVariable Integer id) {
        return postService.deleteById(id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Integer id) {
        return postService.getById(id);
    }

    @GetMapping("/{userId}")
    public PostResponse getPostByUserId(@PathVariable Integer userId) {
        return postService.postsByUser(userId);
    }
}
