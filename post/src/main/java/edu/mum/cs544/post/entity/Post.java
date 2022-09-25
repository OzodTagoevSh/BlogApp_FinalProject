package edu.mum.cs544.post.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String content;
    private LocalDate createdTime;
    private LocalDate updatedTime;
    private Integer userId;

    public Post(String title, String content, LocalDate createdTime, LocalDate updatedTime, Integer userId) {
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.userId = userId;
    }
}
