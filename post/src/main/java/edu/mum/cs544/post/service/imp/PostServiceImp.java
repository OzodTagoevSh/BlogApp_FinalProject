package edu.mum.cs544.post.service.imp;

import edu.mum.cs544.post.dto.PostDto;
import edu.mum.cs544.post.entity.Post;
import edu.mum.cs544.post.repository.PostRepository;
import edu.mum.cs544.post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> getAll() {
        List<Post> posts = repository.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addPost(PostDto post) {
        repository.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Post post = repository.findById(id).orElseThrow(() -> new Exception("Post not Found"));
        repository.deleteById(id);
    }

    @Override
    public void updatePost(Integer id, PostDto postDto) {
        Post post = repository.findById(id).orElse(null);
        if(post != null) {
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setCreatedTime(postDto.getCreatedTime());
            repository.save(post);
        }
    }

    @Override
    public PostDto getById(Integer id) throws Exception {
        Post post = repository.findById(id).orElseThrow(() -> new Exception("Post not Found"));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> postsByUser(Integer userId) {
        List<Post> postsByUser = repository.findAllByUserId(userId);
        List<PostDto> result = postsByUser.stream()
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
        return result;
    }
}
