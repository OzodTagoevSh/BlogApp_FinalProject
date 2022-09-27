package edu.mum.cs544.post.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs544.post.dto.*;
import edu.mum.cs544.post.entity.Post;
import edu.mum.cs544.post.repository.PostRepository;
import edu.mum.cs544.post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

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
    public PostResponse deletePost(Integer userId, Integer postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post != null && post.getUserId().equals(userId)) {
            postRepository.delete(post);
            return new PostResponse(true, "Post deleted successfully!", null);
        } else if (!post.getUserId().equals(userId)) {
            return new PostResponse(true, "You cannot update this post", null);
        } else
            return new PostResponse(false, "Post not found!", null);

    }

    @Override
    public PostResponse updatePost(Integer userId, Integer postId, PostRequest postRequest) {
        Post post = postRepository.findById(userId).orElse(null);
        if (post != null && post.getUserId().equals(userId)) {
            LocalDate date = LocalDate.now();
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setUpdatedTime(date);
            postRepository.save(post);
            return new PostResponse(true, "Post updated successfully!", null);
        } else if (!post.getUserId().equals(userId)) {
            return new PostResponse(true, "You can not update this post", null);
        }
        return new PostResponse(false, "Post not found!", null);
    }

    @Override
    public PostResponse getPost(Integer id) throws JsonProcessingException  {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return new PostResponse(false, "Post not found!", null);
        PostResponse comments = restTemplate.getForObject("http://COMMENT/comments/post/{postId}", PostResponse.class, post.getId());
        PostResponse user = restTemplate.getForObject("http://USER/users/{id}", PostResponse.class, post.getUserId());
        if(!comments.isSuccess() || comments.getData() == null)
            return comments;
        if(!user.isSuccess() || user.getData() == null)
            return user;
        User user1 = objectMapper.readValue(objectMapper.writeValueAsString(user.getData()), new TypeReference<User>() {});
        PostComments postComments = PostComments.builder()
                .id(post.getId())
                .user(user1)
                .content(post.getContent())
                .title(post.getTitle())
                .comments((List<Comment>) comments.getData())
                .build();
        return new PostResponse(true, postComments);
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
