package edu.mum.cs544.comment.controller;

import edu.mum.cs544.comment.dto.CommentRequest;
import edu.mum.cs544.comment.dto.CommentResponse;
import edu.mum.cs544.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public CommentResponse getAll() {
        return commentService.getAll();
    }

    @PostMapping("/{userId}")
    public CommentResponse addComment(@PathVariable Integer userId, @RequestBody CommentRequest commentRequest) {
        return commentService.addComment(userId, commentRequest);
    }

    @DeleteMapping("/{id}")
    public CommentResponse deleteComment(@PathVariable Integer id) {
        return commentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CommentResponse updateComment(@PathVariable Integer id, @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(id, commentRequest);
    }

    @GetMapping("/{id}")
    public CommentResponse getById(@PathVariable Integer id) {
        return commentService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public CommentResponse commentsByUserId(@PathVariable Integer userId) {
        return commentService.commentsByUser(userId);
    }

    @GetMapping("/post/{postId}")
    public CommentResponse commentsByPostId(@PathVariable Integer postId) {
        return commentService.commentsByPostId(postId);
    }
}
