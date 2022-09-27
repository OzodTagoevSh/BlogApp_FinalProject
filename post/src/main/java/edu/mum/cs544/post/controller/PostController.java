package edu.mum.cs544.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Integer postId, @RequestHeader(value = "id") Integer userId, @RequestBody PostRequest postRequest) {
        return postService.updatePost(userId, postId, postRequest);
    }

    @DeleteMapping("/{postId}")
    public PostResponse deletePost(@PathVariable Integer postId, @RequestHeader(value = "id") Integer userId) {
        return postService.deletePost(userId, postId);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Integer id) throws JsonProcessingException {
        return postService.getPost(id);
    }

    @GetMapping("/me")
    public PostResponse getPostByUserId(@RequestHeader(value = "id") Integer userId) {
        return postService.postsByUser(userId);
    }
}
