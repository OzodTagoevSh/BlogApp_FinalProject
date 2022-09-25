package edu.mum.cs544.post.dto;

import java.time.LocalDate;

public class Comment {
    private Integer id;

    private String comment;
    private Integer userId;
    private Integer postId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
