package edu.mum.cs544.post.service.imp;

import edu.mum.cs544.post.dto.PostDto;
import edu.mum.cs544.post.dto.PostRequest;
import edu.mum.cs544.post.dto.PostResponse;
import edu.mum.cs544.post.entity.Post;
import edu.mum.cs544.post.repository.PostRepository;
import edu.mum.cs544.post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostResponse getAll() {
        return new PostResponse(true, postRepository.findAll());
    }

    @Override
    public PostResponse addPost(Integer userId, PostRequest postRequest) {
        LocalDate date = LocalDate.now();
        Post post = postRepository.save(new Post(postRequest.getTitle(), postRequest.getContent(), date, date, userId));
        return new PostResponse(true, "Post created successfully!", modelMapper.map(post, PostDto.class));
    }

    @Override
    public PostResponse deleteById(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return new PostResponse(false, "Post not found!", null);
        postRepository.delete(post);
        return new PostResponse(true, "Post deleted successfully!", null);
    }

    @Override
    public PostResponse updatePost(Integer id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return new PostResponse(false, "Post not found!", null);
        LocalDate date = LocalDate.now();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setUpdatedTime(date);
        postRepository.save(post);
        return new PostResponse(true, "Post updated successfully!", null);
    }

    @Override
    public PostResponse getById(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return new PostResponse(false, "Post not found!", null);
        postRepository.delete(post);
        return new PostResponse(true, "Post deleted successfully!", null);
    }

    @Override
    public PostResponse postsByUser(Integer userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        if(posts == null)
            return new PostResponse(false, "Post not found for this user!", null);
        List<PostDto> result = posts.stream().map(p -> modelMapper.map(p, PostDto.class)).toList();
        return new PostResponse(true, "Posts found!", result);
    }
}
