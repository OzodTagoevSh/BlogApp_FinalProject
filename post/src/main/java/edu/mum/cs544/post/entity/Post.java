package edu.mum.cs544.post.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String content;
    private LocalDateTime createdTime;
}
