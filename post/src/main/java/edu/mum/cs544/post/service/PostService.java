package edu.mum.cs544.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.mum.cs544.post.dto.PostDto;
import edu.mum.cs544.post.dto.PostRequest;
import edu.mum.cs544.post.dto.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponse getAll();
    PostResponse addPost(Integer userId, PostRequest postRequest);
    PostResponse deletePost(Integer userId, Integer postId);
    PostResponse updatePost(Integer userId, Integer postId, PostRequest postRequest);
    PostResponse getPost(Integer id) throws JsonProcessingException;
    PostResponse postsByUser(Integer userId);

}
