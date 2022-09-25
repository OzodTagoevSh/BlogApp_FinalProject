package edu.mum.cs544.comment.service;

import edu.mum.cs544.comment.dto.CommentDto;
import edu.mum.cs544.comment.dto.CommentRequest;
import edu.mum.cs544.comment.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse getAll();
    CommentResponse addComment(Integer userId, CommentRequest commentRequest);
    CommentResponse deleteById(Integer id);
    CommentResponse updateComment(Integer id, CommentRequest commentRequest);
    CommentResponse getById(Integer id);

    CommentResponse commentsByUser(Integer userId);
    CommentResponse commentsByPostId(Integer postId);
}
