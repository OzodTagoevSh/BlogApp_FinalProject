package edu.mum.cs544.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private LocalDateTime createdTime;
}
