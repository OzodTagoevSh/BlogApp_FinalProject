package edu.mum.cs544.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedTime;
    private Integer userId;
}
