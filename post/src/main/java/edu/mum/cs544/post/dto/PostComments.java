package edu.mum.cs544.post.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class PostComments {
    private Long id;
    private String title;
    private String content;
    private User user;
    private List<Comment> comments = new ArrayList<>();
}
