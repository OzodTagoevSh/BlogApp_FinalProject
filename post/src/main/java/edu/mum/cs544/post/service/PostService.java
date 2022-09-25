package edu.mum.cs544.post.service;

import edu.mum.cs544.post.dto.PostDto;
import edu.mum.cs544.post.dto.PostRequest;
import edu.mum.cs544.post.dto.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponse getAll();
    PostResponse addPost(Integer userId, PostRequest postRequest);
    PostResponse deleteById(Integer id);
    PostResponse updatePost(Integer id, PostRequest postRequest);
    PostResponse getById(Integer id);
    PostResponse postsByUser(Integer userId);

}
