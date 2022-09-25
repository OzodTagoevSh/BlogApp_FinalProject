package edu.mum.cs544.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    private String comment;
    private Integer userId;
    private Integer postId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Comment(String comment, Integer userId, Integer postId, LocalDate createdAt, LocalDate updatedAt) {
        this.comment = comment;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
