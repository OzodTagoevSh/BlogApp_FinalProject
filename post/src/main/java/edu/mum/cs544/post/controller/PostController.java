package edu.mum.cs544.post.controller;

import edu.mum.cs544.post.dto.PostDto;
import edu.mum.cs544.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> getAll() {
        return postService.getAll();
    }

    @PostMapping
    public void addPost(@RequestBody PostDto postDto) {
        postService.addPost(postDto);
    }

    @PostMapping("/{id}")
    public void updatePost(@PathVariable Integer id, @RequestBody PostDto postDto) {
        postService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) throws Exception {
        postService.deleteById(id);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Integer id) throws Exception {
        return postService.getById(id);
    }
}
