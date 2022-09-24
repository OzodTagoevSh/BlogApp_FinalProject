package edu.mum.cs544.post.service;

import edu.mum.cs544.post.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> getAll();
    void addPost(PostDto post);
    void deleteById(Integer id) throws Exception;
    void updatePost(Integer id, PostDto post);
    PostDto getById(Integer id) throws Exception;

    List<PostDto> postsByUser(Integer userId);

}
